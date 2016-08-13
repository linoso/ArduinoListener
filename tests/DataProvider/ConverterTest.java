package DataProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ConverterTest {

    Converter sut;
    Properties prop;
    @Before
    public void setUp() throws Exception {
        prop = new Properties();
        prop.setProperty("temp1","10");
        prop.setProperty("temp2","2");
        prop.setProperty("temp3","3");
        prop.setProperty("temp4","4");
        prop.setProperty("ampere","1.5");
        prop.setProperty("volt","2.5");
        prop.setProperty("pressure","3.5");
        sut  = new Converter(new Calibrator(prop) );
    }

    @Test
    public void testToSingleReadNullElementReturnNull() throws Exception {
        String str = null;
        SingleRead sr = sut.toSingleRead(str);

        assertNull(sr);
    }

    @Test
    public void testToSingleReadWrongElementReturnNull() throws Exception {
        String str = "wrongString";
        SingleRead sr = sut.toSingleRead(str);

        assertNull(sr);
    }
    @Test
    public void testToSingleReadNanElementReturnNull() throws Exception {
        String str = "wrongString";
        SingleRead sr = sut.toSingleRead(str);

        assertNull(sr);
    }


    @Test
    public void testToSingleRead() throws Exception {
        String str = "+123.00;+456.55;+789.50;+321.05;+654.50;+987.51;+231.49;";
        SingleRead sr = sut.toSingleRead(str);

        SingleRead expected = new SingleRead();
        assertEquals(1230,sr.getTemp1());
        assertEquals(914, sr.getTemp2());
        assertEquals(2367, sr.getTemp3());
        assertEquals(1284, sr.getTemp4());
        assertEquals(1636.25, sr.getVolt().doubleValue());
        assertEquals(1481.2649999999999,sr.getAmpere().doubleValue());
        assertEquals(810.215,sr.getPressure().doubleValue());

    }

    public void testToSingleReadWithNan() throws Exception {
        String str = "+123.45;+456.65;nan;321.12;nan;+987.51;+231.49;";
        SingleRead sr = sut.toSingleRead(str);

        SingleRead expected = new SingleRead();
        assertEquals(1230,sr.getTemp1());
        assertEquals(914, sr.getTemp2());
        assertNull(sr.getTemp3());
        assertEquals(1284, sr.getTemp4());
        assertNull(sr.getVolt());
        assertEquals(2468.775,sr.getAmpere().doubleValue());
        assertEquals(810.215,sr.getPressure().doubleValue());

        assertEquals(expected,sr);
    }
}