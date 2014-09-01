import DataConsumer.ConsumerInterface;
import DataConsumer.Graphite;
import DataConsumer.NetworkProtocol.UdpProtocol;
import DataConsumer.NetworkProtocolInterface;
import DataProvider.ProviderInterface;
import DataProvider.SerialPortProvider;
import DataProvider.SingleRead;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Created by Lino on 29/08/2014.
 */
public class Runner {
    public static void main(String[] args) {
        NetworkProtocolInterface network = new UdpProtocol("graphite", 2003);
        ConsumerInterface cons = new Graphite(network);
        ProviderInterface prov = new SerialPortProvider();
        BlockingQueue<SingleRead> queue = new ArrayBlockingQueue<SingleRead>(100);
        Processer proc = new Processer(cons,queue);
        DeviceDataReader reader = new DeviceDataReader(prov,queue);
        new Thread(proc).start();
        new Thread(reader).start();
    }
}



