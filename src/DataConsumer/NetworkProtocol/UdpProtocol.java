package DataConsumer.NetworkProtocol;

import DataConsumer.NetworkProtocolInterface;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;

/**
 * Created by Lino on 31/08/2014.
 */
public class UdpProtocol implements NetworkProtocolInterface {
    private int port;
    private String host;
    private InetAddress address;
    private DatagramSocket clientSocket;
    static Logger logger = Logger.getLogger(UdpProtocol.class.getName());
    public UdpProtocol(String host, int port) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void initialize() throws UnknownHostException {

        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            logger.error("Unknown Host: "+host+", impossible to create a connection. Localhost will be used.", e);
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e1) {
                logger.error("Neither localhost works, nothing else can be done", e1);
                throw e1;
            }
        }
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String msg) {
        byte[] sendData;
        sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            logger.error("Cannot send a  message. I'll try again with the next one", e);
        }
    }


    public void close(){
        clientSocket.close();
    }

    public void finalize(){
        close();
    }
}
