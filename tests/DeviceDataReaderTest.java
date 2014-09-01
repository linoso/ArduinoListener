import DataProvider.RandomGenerator;
import DataProvider.SingleRead;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

public class DeviceDataReaderTest {

    DeviceDataReader sut;
    BlockingQueue<SingleRead> queue;
    @Before
    public void setUp() throws Exception {
        queue = new ArrayBlockingQueue<SingleRead>(5);
        sut = new DeviceDataReader(new RandomGenerator(),queue);
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