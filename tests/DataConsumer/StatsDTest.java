package DataConsumer;

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
         SingleRead read = new SingleRead();
         read.setTemp1((float)300+(float)Math.random()*(float)20);
         read.setTemp2((float)300+(float)Math.random()*(float)20);
         read.setTemp3((float)600+(float)Math.random()*(float)40);
         read.setTemp4((float)600+(float)Math.random()*(float)40);
         read.setAmpere((float)20+(float)Math.random()*(float)5);
         read.setPressure((float)600+(float)Math.random()*(float)100);
         read.setVolt((float)20+(float)Math.random()*(float)20);
         return read;
     }
}