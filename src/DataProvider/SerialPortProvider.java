package DataProvider;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.apache.log4j.Logger;

import javax.management.RuntimeErrorException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Lino on 30/08/2014.
 */

public class SerialPortProvider implements SerialPortEventListener,  ProviderInterface {


    static Logger logger = Logger.getLogger(SerialPortProvider.class.getName());
    private final Calibrator calibrator;
    SerialPort serialPort;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyAMA0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM4", // Windows
    };


    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;


    private BlockingQueue<String> internalQueue;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 115200;

    private Converter converter = null;

    public SerialPortProvider(Calibrator calibrator) throws Exception {
        this.calibrator = calibrator;
        converter  = new Converter(this.calibrator);
        this.initialize();
    }

    @Override
    public void refreshConfigs() {
        calibrator.reloadFromFile();
    }

    @Override
    public SingleRead read() {
        try {
            String st = internalQueue.take();
            return converter.toSingleRead(st);
        } catch (InterruptedException e) {
            logger.warn("Some problem with the internalQueue while waiting for the new data to read", e);
        }
        return null;
    }

    public void initialize() throws Exception {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyAMA0");

        internalQueue = new ArrayBlockingQueue<String>(50);

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        logger.info("Before entering the elements for the serial port");
        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                logger.info("Found serial port Name: " + currPortId.getName());
                if (currPortId.getName().equals(portName)) {
                    logger.info("Found one of the valid port: "+currPortId.getName());
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            String msg = "Could not find Serial port, please verify if the Arduino Device is properly connected to one of the available serial interfaces";
            logger.error(msg);
            throw new RuntimeException(msg);
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            logger.error("Cannot open the serial port.", e);
            throw e;
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                if(internalQueue.remainingCapacity()>0){
                    internalQueue.add(inputLine);
                    logger.debug("Read line from serial port and added to the internal queue:"+inputLine);
                } else {
                    logger.warn("No room left in the internal queue from the port to read and parse. the line "+
                            inputLine+" will be skipped");
                }
            } catch (Exception e) {
                logger.error("Cannot read the line from the serial port ", e);
            }
        }
    }

    public void finalize()
    {
        close();
    }
}


