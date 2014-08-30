import DataConsumer.ConsumerInterface;
import DataProvider.SingleRead;

/**
 * Created by Lino on 19/08/2014.
 */
public class ConsumerTest implements ConsumerInterface {

    SingleRead read;

    public SingleRead getRead() {
        return read;
    }

    public void setRead(SingleRead read) {
        this.read = read;
    }

    @Override
    public void publish(SingleRead read) {
        setRead(read);
    }
}
