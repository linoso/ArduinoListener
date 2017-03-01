import DataProvider.ProviderInterface;
import DataProvider.SingleRead;

/**
 * Created by Lino on 19/08/2014.
 */
public class ProviderTest implements ProviderInterface {

    SingleRead read;

    public SingleRead getRead() {
        return read;
    }

    public void setRead(SingleRead read) {
        this.read = read;
    }

    @Override
    public void refreshConfigs() {
        return;
    }
    @Override
    public SingleRead read() {
        return getRead();
    }
}
