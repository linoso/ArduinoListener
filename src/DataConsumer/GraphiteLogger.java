package DataConsumer;

/**
 * Created by Lino on 29/08/2014.
 */



import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;


public class GraphiteLogger {
    private String graphiteHost;

    private int graphitePort;

    public String getGraphiteHost() {
        return graphiteHost;
    }

    public void setGraphiteHost(String graphiteHost) {
        this.graphiteHost = graphiteHost;
    }

    public int getGraphitePort() {
        return graphitePort;
    }

    public void setGraphitePort(int graphitePort) {
        this.graphitePort = graphitePort;
    }

    public void logToGraphite(Map list) throws Exception {
        Long curTimeInSec = System.currentTimeMillis() / 1000;
        StringBuffer lines = new StringBuffer();
        Iterator iterator = list.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            String key = getHostName() + "." + mapEntry.getKey();
            lines.append(key).append(" ").append(mapEntry.getValue()).append(" ").append(curTimeInSec).append("\n"); //even the last line in graphite
        }
        try {
            logToGraphite(lines);
        } catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }
    public void logToGraphite(String key, long value) throws Throwable {
        Map stats = new HashMap();
        stats.put(key, value);
        try {
            logToGraphite(key, String.valueOf(value));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void logToGraphite(String stats, String value) throws Throwable {
        if (stats.isEmpty()) {
            return;
        }

        try {
            String nodeIdentifier = getHostName();
            logToGraphite(nodeIdentifier, stats, value);
        } catch (Throwable t) {
            System.out.println("Can't log to graphite ");
            System.out.println(t.toString());
            throw t;
        }
    }

    private String getHostName()  {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e){
            return "StirlingEngine";
        }
    }

    private void logToGraphite(String nodeIdentifier, String stats, String value) throws Exception {
        Long curTimeInSec = System.currentTimeMillis() / 1000;
        StringBuffer lines = new StringBuffer();

            String key = nodeIdentifier + "." + stats;
            lines.append(key).append(" ").append(value).append(" ").append(curTimeInSec).append("\n"); //even the last line in graphite

        logToGraphite(lines);
    }

    private void logToGraphite(StringBuffer lines) throws Exception {
        String msg = lines.toString();
        System.out.println("Writing [{" + msg + "}] to graphite");
        Socket socket = new Socket(graphiteHost, graphitePort);
        try {
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(msg);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            socket.close();
        }
    }

}


