package DataConsumer;

import DataProvider.RandomGenerator;
import DataProvider.SingleRead;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatsDTest {

    StatsD sut;
    @Before
    public void setUp() throws Exception {
        sut = new StatsD("192.168.0.120", 2003);
    }

    @Test
    public void testPublish() throws Exception {
        int count = 0;
        SingleRead read = null;
        while(count < 1000) {
            sut.publish(generateRandomSingleRead());
            Thread.sleep(100);
        }
    }
     private SingleRead generateRandomSingleRead(){
         return new RandomGenerator().read();
     }
}