package DataConsumer;

import java.net.UnknownHostException;

/**
 * Created by Lino on 31/08/2014.
 */
public interface NetworkProtocolInterface {
    void initialize() throws Exception;
    void send(String msg);
    void close();
}
