package DataConsumer;


        import DataConsumer.NetworkProtocol.TcpProtocol;
        import DataConsumer.NetworkProtocol.UdpProtocol;
        import DataProvider.RandomGenerator;
        import DataProvider.SingleRead;
        import org.junit.Before;
        import org.junit.Test;

public class GraphiteLoggerTest {

    Graphite sut;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPublishUdp() throws Exception {
        UdpProtocol network = new UdpProtocol("graphite", 2003);
        sut = new Graphite(network);
        int count = 0;
        while(count < 10) {
            sut.publish(generateRandomSingleRead());
            Thread.sleep(100);
            count++;
        }
    }

    @Test
    public void testPublishTcp() throws Exception {
        TcpProtocol network = new TcpProtocol("graphite", 2003);
        sut = new Graphite(network);
        int count = 0;
        while(count < 10) {
            sut.publish(generateRandomSingleRead());
            Thread.sleep(100);
            count++;
        }
    }
    private SingleRead generateRandomSingleRead(){
        return new RandomGenerator().read();
    }

}