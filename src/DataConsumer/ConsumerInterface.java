package DataConsumer;
import DataProvider.SingleRead;

/**
 * Created by Lino on 19/08/2014.
 */
public interface ConsumerInterface {
    public void publish(SingleRead read) throws Exception;
}
