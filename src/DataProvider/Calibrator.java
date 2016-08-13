package DataProvider;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Lino on 09/04/2016.
 */
public class Calibrator {
    HashMap<String, Double> config;
    public Calibrator(Properties prop) {
        config = new HashMap<String, Double>(8);
        config.put("t1",Double.parseDouble(prop.getProperty("temp1")));
        config.put("t2",Double.parseDouble(prop.getProperty("temp2")));
        config.put("t3",Double.parseDouble(prop.getProperty("temp3")));
        config.put("t4",Double.parseDouble(prop.getProperty("temp4")));
        config.put("amp",Double.parseDouble(prop.getProperty("ampere")));
        config.put("volt",Double.parseDouble(prop.getProperty("volt")));
        config.put("pres",Double.parseDouble(prop.getProperty("pressure")));
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
        return value*caliber;
    }
}
