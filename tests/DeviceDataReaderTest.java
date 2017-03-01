import DataProvider.Calibrator;
import DataProvider.RandomGenerator;
import DataProvider.SingleRead;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DeviceDataReaderTest {

    DeviceDataReader sut;
    BlockingQueue<SingleRead> queue;
    @Before
    public void setUp() throws Exception {
        Properties prop = new Properties();
        prop.setProperty("temp1","10");
        prop.setProperty("temp2","2");
        prop.setProperty("temp3","3");
        prop.setProperty("temp4","4");
        prop.setProperty("ampere","1.5");
        prop.setProperty("volt","2.5");
        prop.setProperty("pressure","3.5");
        queue = new ArrayBlockingQueue<SingleRead>(5);
        sut = new DeviceDataReader(new RandomGenerator(new Calibrator(prop, null)),queue);
    }

    @Test
    public void testRun() throws Exception {
        Thread thread =  new Thread(sut);
        thread.start();
        int count = 0;
        while(count<5){
            SingleRead read = queue.take();
            System.out.println("found one element");
            count++;
        }
        thread.interrupt();


    }
}