package DataConsumer;

import DataProvider.SingleRead;

/**
 * Created by Lino on 29/08/2014.
 */
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
public class Graphite implements ConsumerInterface {

    GraphiteLogger logger;
    Graphite(){
        logger  = new GraphiteLogger();
        logger.setGraphitePort(2003);
        logger.setGraphiteHost("graphite");
    }

    @Override
    public void publish(SingleRead read) {
        Map sr = new HashMap();
        sr.put("pressure", String.valueOf(read.getPressure()));
        sr.put("temp1", String.valueOf(read.getTemp1()));
        sr.put("temp2", String.valueOf(read.getTemp2()));
        sr.put("temp3", String.valueOf(read.getTemp3()));
        sr.put("temp4", String.valueOf(read.getTemp4()));
        sr.put("ampere", String.valueOf(read.getAmpere()));
        sr.put("volt", String.valueOf(read.getVolt()));
        sr.put("Watt", String.valueOf(read.getVolt()*read.getAmpere()));
        logger.logToGraphite(sr);
    }
}

