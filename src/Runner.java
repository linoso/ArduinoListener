import DataConsumer.ConsumerInterface;
import DataConsumer.Graphite;
import DataConsumer.NetworkProtocol.TcpProtocol;
import DataConsumer.NetworkProtocol.UdpProtocol;
import DataConsumer.NetworkProtocolInterface;
import DataProvider.ProviderInterface;
import DataProvider.RandomGenerator;
import DataProvider.SerialPortProvider;
import DataProvider.SingleRead;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Lino on 29/08/2014.
 */
public class Runner {
    static Logger logger = Logger.getLogger(Runner.class.getName());
    static String logConfFile = "logConfigDeamon.properties";
    static String appConfFile = "appConfig.properties";
    static AppConfigs appConfigs = new AppConfigs();

    public static void main(String[] args)  {

        readCommandLineInputs(args);
        PropertyConfigurator.configure(logConfFile);
        if (loadAppConfig()) return;

        logger.info("Entering application.");
        NetworkProtocolInterface network = createNetworkProtocolInterface();
        ConsumerInterface cons;
        try {
            cons = createConsumerInterface(network);
        } catch (Exception e) {
           logger.error("cannot create the ConsumerInterface, no point in continuing with the sw", e);
           return;
        }
        ProviderInterface prov = null;
        try {
            prov = createProviderInterface();
        } catch (Exception e) {
            logger.error("cannot create the ProviderInterface, no point in continuing with the sw", e);
            return;
        }
        BlockingQueue<SingleRead> queue = new ArrayBlockingQueue<SingleRead>(100);
        Processer proc = new Processer(cons,queue);
        DeviceDataReader reader = new DeviceDataReader(prov,queue);
        new Thread(proc).start();
        new Thread(reader).start();
        logger.info("Exiting application.");
    }

    private static boolean loadAppConfig() {
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(appConfFile);
            props.load(fis);
        } catch (FileNotFoundException e) {
            String msg = "Cannot Find Properties file  for the application, looking in path:" + appConfFile;
            logOnLoggerAndStdOut(e, msg);
            return true;
        } catch (IOException e) {
            String msg = "Cannot read Properties file  for the application, looking in path:" + appConfFile;
            logOnLoggerAndStdOut(e, msg);
            return true;
        }
        appConfigs.setHostname(props.getProperty("database.host"));
        appConfigs.setProtocol(props.getProperty("database.protocol"));
        appConfigs.setPort(Integer.parseInt(props.getProperty("database.port")));
        appConfigs.setDataConsumer(props.getProperty("dataConsumer.class"));
        appConfigs.setDataProvider(props.getProperty("dataProvider.class"));
        return false;
    }

    private static Graphite createConsumerInterface(NetworkProtocolInterface network) throws Exception {
        return new Graphite(network);
    }

    private static ProviderInterface createProviderInterface() throws Exception {
        ProviderInterface pi = null;
        dataProvider enumval = dataProvider.valueOf(appConfigs.getDataProvider());
        switch (enumval){
            case SERIAL:
                logger.info("Creating  a SerialPortProvider class");
                pi = new SerialPortProvider ();
                break;
            case RANDOM:
                logger.info("Creating  a Random Provider class");
                pi = new RandomGenerator();
                break;
            default:
                pi = new SerialPortProvider ();
                logger.error("appConfigs.getProtocol should return 'SERIAL' or 'RANDOM' instead "
                        +appConfigs.getDataProvider()+" . please change the value for dataprovider.class in appconfig.properties");
        }
        return pi;
    }

    private static NetworkProtocolInterface createNetworkProtocolInterface() {
        NetworkProtocolInterface npi = null;
        protocol enumval = protocol.valueOf(appConfigs.getProtocol());
        switch (enumval){
            case TCP:
                logger.info("Creating  a TcpProtocol class");
                npi = new TcpProtocol(appConfigs.getHostname(),appConfigs.getPort());
                break;
            case UDP:
                logger.info("Creating  a UdpProtocol class");
                npi = new UdpProtocol(appConfigs.getHostname(),appConfigs.getPort());
                break;
            default:
                npi = new UdpProtocol(appConfigs.getHostname(),appConfigs.getPort());
                logger.error("appConfigs.getProtocol should return 'tcp' or 'udp' instead "
                        +appConfigs.getProtocol()+" . please change the value for database.protocol in appconfig.properties");
        }
        return npi;
    }

    private static void logOnLoggerAndStdOut(Throwable e, String msg) {
        System.out.println(msg);
        logger.error(msg, e);
        e.printStackTrace();
    }

    private static void readCommandLineInputs(String[] args) {
        if(args.length>=1){
            appConfFile = args[0];
        }
        if(args.length>=2){
            logConfFile = args[1];
        }
    }
    private enum protocol {
        TCP, UDP
    }

    private enum dataProvider{
        SERIAL, RANDOM
    }
}





