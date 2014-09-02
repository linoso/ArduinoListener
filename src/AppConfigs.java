/**
 * Created by Lino on 01/09/2014.
 */
public class AppConfigs {
    private String hostname = null;
    private Integer port = null;
    private String protocol = null;
    private String dataProvider = null;
    private String dataConsumer =  null;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDataProvider() {
        return dataProvider;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setDataProvider(String dataProvider) {
        this.dataProvider = dataProvider;
    }

    public String getDataConsumer() {
        return dataConsumer;
    }

    public void setDataConsumer(String dataConsumer) {
        this.dataConsumer = dataConsumer;
    }
}
