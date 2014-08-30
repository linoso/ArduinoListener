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
        Processer proc = new Processer(prov, cons);
        try {
            while (true) {
                try {
                    proc.processData();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (Exception e) {
            System.out.println("exiting beacuse of the exception");
        }
    }
}



