package DataConsumer;

/**
 * Created by Lino on 29/08/2014.
 */



import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.*;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;


public class  GraphiteLogger {
    private NetworkProtocolInterface network;

    public GraphiteLogger(NetworkProtocolInterface network) {
        this.network = network;
    }

    public void logToGraphite(Map list) throws Exception {
        Long curTimeInSec = System.currentTimeMillis() / 1000;
        StringBuffer lines = createLogEntry(list, curTimeInSec);
        try {
            System.out.println("Writing [{" + lines.toString() + "}] to graphite");
            network.send(lines.toString());
        } catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    private StringBuffer createLogEntry(Map list, Long curTimeInSec) {
        StringBuffer lines = new StringBuffer();
        Iterator iterator = list.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry mapEntry = (Entry) iterator.next();
            String key = getHostName() + "." + mapEntry.getKey();
            lines.append(key).append(" ").append(mapEntry.getValue()).append(" ").append(curTimeInSec).append("\n"); //even the last line in graphite
        }
        return lines;
    }


    private String getHostName()  {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e){
            return "StirlingEngine";
        }
    }

}


