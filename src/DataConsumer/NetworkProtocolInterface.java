package DataConsumer;

/**
 * Created by Lino on 31/08/2014.
 */
public interface NetworkProtocolInterface {
    void initialize();
    void send(String msg);
    void close();
}
