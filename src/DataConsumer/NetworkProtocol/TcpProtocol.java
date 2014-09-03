package DataConsumer.NetworkProtocol;

import DataConsumer.NetworkProtocolInterface;
import org.apache.log4j.Logger;

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
    static Logger logger = Logger.getLogger(UdpProtocol.class.getName());

    public TcpProtocol(String host,int port) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void initialize() throws IOException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            logger.error("Cannot create a Socket instance, nothing can be done here", e);
            throw e;
        }
    }

    @Override
    public void send(String msg) {
        try {
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(msg);
            writer.flush();
        } catch (Exception e) {
            logger.error("Cannot send a package, I'll try with the next one", e);
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("cannot close the socket properly, it will stay open...", e);
        }
    }
}
