package DataProvider;

import org.apache.log4j.Logger;

/**
 * Created by Lino on 29/08/2014.
 */
public class RandomGenerator implements ProviderInterface {
    static Logger logger = Logger.getLogger(RandomGenerator.class.getName());
    private Calibrator calibrator;

    public RandomGenerator(Calibrator calibrator) {
        this.calibrator = calibrator;
    }

    @Override
    public SingleRead read() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Thread Sleep failed ", e);
        }
        return generateRandomSingleRead();
    }

    @Override
    public void refreshConfigs() {
        calibrator.reloadFromFile();
    }

    private SingleRead generateRandomSingleRead(){
        SingleRead read = new SingleRead();
        read.setTemp1(calibrator.adjustTemp1(300+(int)(Math.random()*20)));
        read.setTemp2(calibrator.adjustTemp2(300+(int)(Math.random()*20)));
        read.setTemp3(calibrator.adjustTemp3(600+(int)(Math.random()*40)));
        read.setTemp4(calibrator.adjustTemp4(600+(int)(Math.random()*40)));
        read.setAmpere(calibrator.adjustAmpere(new Double((double)(20+(int)(Math.random()*5)))));
        read.setPressure(calibrator.adjustPressure(new Double((double)(600+(int)(Math.random()*40)))));
        read.setVolt(calibrator.adjustVolt(new Double((double)(20+(int)(Math.random()*20)))));
        return read;
    }
}
