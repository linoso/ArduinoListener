import DataProvider.SingleRead;

import static org.junit.Assert.*;

public class ProcesserTest {

    Processer sut;
    ProviderTest provider;
    ConsumerTest consumer;
    @org.junit.Before
    public void setUp() throws Exception {
        consumer = new ConsumerTest();
        provider = new ProviderTest();
        sut = new Processer(provider,consumer);
    }

    @org.junit.Test
    public void testRead() throws Exception {
        SingleRead expected = new SingleRead();
        provider.setRead(expected);
        sut.processData();
        SingleRead read = consumer.getRead();
        assertEquals(expected,read);
    }
}

