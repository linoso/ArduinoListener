package DataConsumer;

import DataProvider.SingleRead;
import java.io.*;
import java.net.*;
import java.net.InetAddress;

/**
 * Created by Lino on 27/08/2014.
 */
public class Carbon implements ConsumerInterface {
    @Override
    public void publish(SingleRead read) {
        try {
            String ip = "192.168.0.120";
            String host = "graphite";
            int port = 2003;

            byte[] message = "local.random.fromjava 4 `date 03234`\n".getBytes();

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length,
                    address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}
