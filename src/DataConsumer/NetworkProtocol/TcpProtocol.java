package DataConsumer.NetworkProtocol;

import DataConsumer.NetworkProtocolInterface;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Lino on 31/08/2014.
 */
public class TcpProtocol implements NetworkProtocolInterface{

    int port;
    String host;
    Socket socket;

    public TcpProtocol(String host,int port) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void initialize() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String msg) {
        try {
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(msg);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
