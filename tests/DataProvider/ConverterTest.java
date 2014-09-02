package DataProvider;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    Converter sut;
    @Before
    public void setUp() throws Exception {
        sut  = new Converter();
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
        expected.setTemp1(123);
        expected.setTemp2(457);
        expected.setTemp3(789);
        expected.setTemp4(321);
        expected.setVolt(654.50);
        expected.setAmpere(987.51);
        expected.setPressure(231.49);

        assertEquals(expected,sr);
    }

    @Test
    public void testToSingleReadWithNan() throws Exception {
        String str = "+123.45;+456.65;nan;321.12;nan;+987.51;+231.49;";
        SingleRead sr = sut.toSingleRead(str);

        SingleRead expected = new SingleRead();
        expected.setTemp1(123);
        expected.setTemp2(457);
        expected.setTemp4(321);
        expected.setAmpere(987.51);
        expected.setPressure(231.49);

        assertEquals(expected,sr);
    }
}