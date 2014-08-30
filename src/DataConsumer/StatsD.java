package DataConsumer;

import DataProvider.SingleRead;
import statsd.StatsDClient;
import statsd.NonBlockingStatsDClient;


/**
 * Created by Lino on 19/08/2014.
 */
public class StatsD implements ConsumerInterface {

    private StatsDClient statsd;

    public StatsD(String ip, int port) {
        statsd = new NonBlockingStatsDClient("StirlingEngine", ip, port);
    }

    @Override
    public void publish(SingleRead read) {

        statsd.recordExecutionTime("temp1", (long) read.getTemp1());
        statsd.recordExecutionTime("temp2", (long) read.getTemp2());
        statsd.recordExecutionTime("temp3", (long) read.getTemp3());
        statsd.recordExecutionTime("temp4", (long) read.getTemp4());
        }
}
