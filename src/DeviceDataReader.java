import DataProvider.ProviderInterface;
import DataProvider.SingleRead;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Lino on 30/08/2014.
 */
public class DeviceDataReader implements Runnable {

    BlockingQueue<SingleRead> queue;
    ProviderInterface provider;
    public DeviceDataReader(ProviderInterface provider, BlockingQueue<SingleRead> queue) {
        this.queue = queue;
        this.provider = provider;
    }

    @Override
    public void run() {
        while (true){
            try {

                Thread.sleep(500);
                provider.refreshConfigs();
                SingleRead data = provider.read();
                if(queue.remainingCapacity()>0) {
                    queue.add(data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
