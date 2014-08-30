package DataProvider;

/**
 * Created by Lino on 29/08/2014.
 */
public class RandomGenerator implements ProviderInterface {

    @Override
    public SingleRead read() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generateRandomSingleRead();
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
