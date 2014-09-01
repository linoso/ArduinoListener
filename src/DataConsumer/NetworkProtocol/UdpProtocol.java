package DataConsumer.NetworkProtocol;

import DataConsumer.NetworkProtocolInterface;

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

    public UdpProtocol(String host, int port) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void initialize() {

        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    public void close(){
        clientSocket.close();
    }

    public void finalize(){
        close();
    }
}
