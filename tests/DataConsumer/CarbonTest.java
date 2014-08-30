package DataConsumer;

import DataProvider.SingleRead;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarbonTest {

    @Test
    public void testName() throws Exception {
        Carbon sut = new Carbon();
        int i  = 0;
        while (i<10){
            Thread.sleep(500);
            i++;
            sut.publish(generateRandomSingleRead());
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