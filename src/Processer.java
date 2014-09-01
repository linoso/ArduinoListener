import DataConsumer.ConsumerInterface;
import DataProvider.ProviderInterface;
import DataProvider.SingleRead;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Lino on 19/08/2014.
 */
public class Processer implements Runnable{
    ConsumerInterface consumer;
    BlockingQueue<SingleRead> queue;

    Processer(ConsumerInterface consumer, BlockingQueue<SingleRead> queue){
        this.queue=queue;
        this.consumer = consumer;
    }


    @Override
    public void run() {
        try{
            SingleRead data;
            //consuming messages until exit message is received
            while(true){
                data = queue.take();
                processData(data);
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processData(SingleRead read)  {
        System.out.println("Consumed one read");
        try {
            consumer.publish(read);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
