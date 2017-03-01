package DataProvider;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Lino on 09/04/2016.
 */
public class Calibrator {

    static Logger logger = Logger.getLogger(Calibrator.class.getName());
    HashMap<String, Double> config;
    String fileName;
    public Calibrator(Properties prop, String fileName) {
        config = new HashMap<String, Double>(8);
        loadProperties(prop);
        this.fileName = fileName;
    }

    public void reloadFromFile()
    {
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e){
            String msg = "Cannot Find Properties file while reloading for the application, looking in path:" + fileName;
            logger.error(msg, e);
            return;
        }
        try {
            props.load(fis);
        } catch (IOException e) {
            String msg = "Cannot reload Properties file  for the application, looking in path:" + fileName;
            logger.error(msg, e);
            return;
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                String msg = "Cannot close file:" + fileName;
                logger.error(msg, e);
            }
        }
        loadProperties(props);
    }

    public void loadProperties(Properties prop) {
        putInConfigValueOrOne(prop, "temp1", "t1");
        putInConfigValueOrOne(prop, "temp2", "t2");
        putInConfigValueOrOne(prop, "temp3", "t3");
        putInConfigValueOrOne(prop, "temp4", "t4");
        putInConfigValueOrOne(prop, "ampere", "amp");
        putInConfigValueOrOne(prop, "volt", "volt");
        putInConfigValueOrOne(prop, "pressure", "pres");
        putInConfigValueOrZero(prop, "t1Tare", "temp1Tare");
        putInConfigValueOrZero(prop, "t2Tare", "temp2Tare");
        putInConfigValueOrZero(prop, "t3Tare", "temp3Tare");
        putInConfigValueOrZero(prop, "t4Tare", "temp4Tare");
        putInConfigValueOrZero(prop, "ampTare", "ampereTare");
        putInConfigValueOrZero(prop, "voltTare", "voltTare");
        putInConfigValueOrZero(prop, "presTare", "pressureTare");
    }

    private void putInConfigValueOrZero(Properties prop, String t1Tare, String temp1Tare) {
        config.put(t1Tare, getValueOrZero(prop, temp1Tare));
    }

    private void putInConfigValueOrOne(Properties prop, String keyProp, String keyConfig) {
        String value = prop.getProperty(keyProp);
        if(value == null){
            value =  "1";
        }
        config.put(keyConfig,Double.parseDouble(value));
    }

    private double getValueOrZero(Properties prop, String key) {
        String value = prop.getProperty(key);
        if(value == null){
            value = "0";
        }
        return Double.parseDouble(value);
    }

    public Integer adjustTemp1(Integer t1 )
    {
        return new Integer(caliberDouble(t1.doubleValue(), "t1").intValue());
    }

    public Integer adjustTemp2(Integer t2 )
    {
        return new Integer(caliberDouble(t2.doubleValue(), "t2").intValue());
    }
    public Integer adjustTemp3(Integer t3 )
    {
        return new Integer(caliberDouble(t3.doubleValue(), "t3").intValue());
    }
    public Integer adjustTemp4(Integer t4 )
    {
        return new Integer(caliberDouble(t4.doubleValue(), "t4").intValue());
    }
    public Double adjustAmpere(Double t4 )
    {
        return caliberDouble(t4, "amp");
    }
    public Double adjustVolt(Double t4 )
    {
        return caliberDouble(t4, "volt");
    }
    public Double adjustPressure(Double t4 )
    {
        return caliberDouble(t4, "pres");
    }


    private Double caliberDouble(Double value, String configIndex) {
        if(null == value){
            throw new InvalidParameterException("Value cannot be null");
        }

        Double caliber = config.get(configIndex);
        Double tare = config.get(configIndex+"Tare");
        return (value*caliber)+tare;
    }
}
