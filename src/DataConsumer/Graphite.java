package DataConsumer;

import DataProvider.SingleRead;
import org.apache.log4j.Logger;

/**
 * Created by Lino on 29/08/2014.
 */
import java.util.HashMap;
import java.util.Map;
public class Graphite implements ConsumerInterface {

    static Logger logger = Logger.getLogger(Graphite.class.getName());
    GraphiteLogger sender;
    public Graphite(NetworkProtocolInterface networkProtocolInterface) throws Exception {
        networkProtocolInterface.initialize();
        sender = new GraphiteLogger(networkProtocolInterface);
    }

    @Override
    public void publish(SingleRead read) throws Exception {
        Map sr = createMapFromSingleReade(read);
        sender.logToGraphite(sr);
    }

    private Map createMapFromSingleReade(SingleRead read) {
        Map sr = new HashMap();
        addMeasureToPacket(sr, "pressure", read.getMilliBar());
        addMeasureToPacket(sr, "temp1", read.getTemp1());
        addMeasureToPacket(sr, "temp2", read.getTemp2());
        addMeasureToPacket(sr, "temp3", read.getTemp3());
        addMeasureToPacket(sr, "temp4", read.getTemp4());
        addMeasureToPacket(sr, "ampere", read.getMilliAmpere());
        addMeasureToPacket(sr, "volt", read.getMilliVolt());
        addMeasureToPacket(sr, "watt", read.getMilliWatt());
        return sr;
    }

    private void addMeasureToPacket(Map sr, String field, Integer value) {
        if(value != null){
            sr.put(field, String.valueOf(value));
        }
    }
}

