import DataConsumer.ConsumerInterface;
import DataConsumer.Graphite;
import DataProvider.ProviderInterface;
import DataProvider.RandomGenerator;

/**
 * Created by Lino on 29/08/2014.
 */
public class Runner {
    public static void main(String[] args) {
        ConsumerInterface cons = new Graphite();
        ProviderInterface prov = new RandomGenerator();
        Processer proc = new Processer(prov,cons);
        while(true){
            proc.processData();
        }
    }


}
