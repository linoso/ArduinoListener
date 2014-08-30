import DataConsumer.ConsumerInterface;
import DataProvider.ProviderInterface;
import DataProvider.SingleRead;

/**
 * Created by Lino on 19/08/2014.
 */
public class Processer {

    ProviderInterface provider;
    ConsumerInterface consumer;
    Processer(ProviderInterface provider, ConsumerInterface consumer){
        this.provider = provider;
        this.consumer = consumer;
    }

    public void processData(){
        SingleRead read = provider.read();
        consumer.publish(read);
    }

}
