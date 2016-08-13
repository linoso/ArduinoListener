import DataConsumer.Graphite;
import DataConsumer.NetworkProtocol.UdpProtocol;
import DataConsumer.NetworkProtocolInterface;
import DataProvider.Calibrator;
import DataProvider.RandomGenerator;
import DataProvider.SingleRead;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProcesserTest {

    Processer sut;
    ProviderTest provider;
    ConsumerTest consumer;
    @org.junit.Before
    public void setUp() throws Exception {
        BlockingQueue<SingleRead> queue = new ArrayBlockingQueue<SingleRead>(5);
        Properties prop = new Properties();
        prop.setProperty("temp1","10");
        prop.setProperty("temp2","2");
        prop.setProperty("temp3","3");
        prop.setProperty("temp4","4");
        prop.setProperty("ampere","1.5");
        prop.setProperty("volt","2.5");
        prop.setProperty("pressure","3.5");
        RandomGenerator rand =  new RandomGenerator(new Calibrator(prop));
        queue.add(rand.read());
        queue.add(rand.read());
        queue.add(rand.read());
        queue.add(rand.read());
        queue.add(rand.read());
        NetworkProtocolInterface nt= new UdpProtocol("graphite", 2003);
        sut =  new Processer(new Graphite(nt),queue);
        sut.run();
    }


}

