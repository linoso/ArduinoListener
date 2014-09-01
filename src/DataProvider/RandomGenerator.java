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
        read.setTemp1((300+(int)(Math.random()*20)));
        read.setTemp2(300+(int)(Math.random()*20));
        read.setTemp3(600+(int)(Math.random()*40));
        read.setTemp4(600+(int)(Math.random()*40));
        read.setAmpere(20+(int)(Math.random()*5));
        read.setPressure(600+(int)(Math.random()*40));
        read.setVolt(20+(int)(Math.random()*20));
        return read;
    }
}
