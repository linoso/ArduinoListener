package DataProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class CalibratorTest {

    Properties prop;
    Calibrator sut;
    @Before
    public void  setup()
    {
        prop = new Properties();
        prop.setProperty("temp1","1.05");
        prop.setProperty("temp2","1.06");
        prop.setProperty("temp3","1.07");
        prop.setProperty("temp4","1.08");
        prop.setProperty("ampere","1.09");
        prop.setProperty("volt","1.10");
        prop.setProperty("pressure","1.11");
        sut = new Calibrator(prop);
    }

    @Test(expected = Exception.class)
    public void calibrateTemp1NullTest(){
        Calibrator sut = new Calibrator(prop);
        sut.adjustTemp1(null);
    }

    @Test
    public void calibrateTemp1PositiveTest() throws Exception {
        assertEquals(105, sut.adjustTemp1(100));
    }

    @Test
    public void calibrateTemp2PositiveTest() throws Exception {
        assertEquals(106, sut.adjustTemp2(100));
    }
    @Test
    public void calibrateTemp3PositiveTest() throws Exception {
        assertEquals(107, sut.adjustTemp3(100));
    }
    @Test
    public void calibrateTemp4PositiveTest() throws Exception {
        assertEquals(108, sut.adjustTemp4(100));
    }
    @Test
    public void calibrateAmpPositiveTest() throws Exception {
        assertEquals(109, sut.adjustAmpere(new Double(100)).intValue());
    }
    @Test
    public void calibrateVoltPositiveTest() throws Exception {
        assertEquals(110, sut.adjustVolt(new Double(100)).intValue());
    }
    @Test
    public void calibratePresPositiveTest() throws Exception {
        assertEquals(111, sut.adjustPressure(new Double(100)).intValue());
    }

}