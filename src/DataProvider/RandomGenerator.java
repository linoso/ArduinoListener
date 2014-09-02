package DataProvider;

import org.apache.log4j.Logger;

/**
 * Created by Lino on 29/08/2014.
 */
public class RandomGenerator implements ProviderInterface {
    static Logger logger = Logger.getLogger(RandomGenerator.class.getName());
    @Override
    public SingleRead read() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Thread Sleep failed ", e);
        }
        return generateRandomSingleRead();
    }

    private SingleRead generateRandomSingleRead(){
        SingleRead read = new SingleRead();
        read.setTemp1((300+(int)(Math.random()*20)));
        read.setTemp2(300+(int)(Math.random()*20));
        read.setTemp3(600+(int)(Math.random()*40));
        read.setTemp4(600+(int)(Math.random()*40));
        read.setAmpere(new Double((double)(20+(int)(Math.random()*5))));
        read.setPressure(new Double((double)(600+(int)(Math.random()*40))));
        read.setVolt(new Double((double)(20+(int)(Math.random()*20))));
        return read;
    }
}
