package DataConsumer;

import DataProvider.RandomGenerator;
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
        return new RandomGenerator().read();
    }
}