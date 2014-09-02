package DataProvider;

import org.apache.log4j.Logger;

import java.util.InvalidPropertiesFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lino on 31/08/2014.
 */
public class Converter {

    static Logger logger = Logger.getLogger(Converter.class.getName());
    Pattern pattern;
    public Converter() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^");
        for(int i = 0 ; i<7; i++){
            stringBuffer.append("((-|\\+)[0-9]+.[0-9][0-9])|nan;");
        }
        stringBuffer.append("$");
        pattern = Pattern.compile(stringBuffer.toString());
    }

    public SingleRead toSingleRead(String line)  {

        if (stringIsNotValid(line)) return null;

        SingleRead sr = new SingleRead();
        String[] elements= line.split(";");
        try {
            sr.setTemp1(convertDoubleStringIntoInt(elements[0]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 0 , "temp1" );
        }
        try {
            sr.setTemp2(convertDoubleStringIntoInt(elements[1]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 1 , "temp2" );
        }
        try {
            sr.setTemp3(convertDoubleStringIntoInt(elements[2]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 2 , "temp3" );
        }
        try {
            sr.setTemp4(convertDoubleStringIntoInt(elements[3]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 3 , "temp4" );
        }
        try {
            sr.setVolt(convertDoubleStringIntoDouble(elements[4]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 4 , "volt" );
        }
        try {
            sr.setAmpere(convertDoubleStringIntoDouble(elements[5]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 5 , "ampere" );
        }
        try {
            sr.setPressure(convertDoubleStringIntoDouble(elements[6]));
        } catch (InvalidPropertiesFormatException e) {
            logWarning(elements, 6 , "pressure" );
        }

        return sr;
    }

    private void logWarning(String elements[] ,int i, String fieldName) {
        logger.warn("returned '" + elements[i] + "' for field " + fieldName);
    }

    private int convertDoubleStringIntoInt(String temp1) throws InvalidPropertiesFormatException {
        if(temp1.equals("nan")){
            throw new InvalidPropertiesFormatException("Found A nan");
        }
        String[] doubleParts = temp1.split("\\.");
        int intPart = Integer.parseInt(doubleParts[0]);
        int decPart = Integer.parseInt(doubleParts[1]);
        if(decPart>50){
            intPart++;
        }
        return intPart;
    }

    private double convertDoubleStringIntoDouble(String num) throws InvalidPropertiesFormatException {
        if(num.equals("nan")){
            throw new InvalidPropertiesFormatException("Found A nan");
        }
        return Double.parseDouble(num);
    }

    private boolean stringIsNotValid(String line) {
        if(line == null){
            logger.error("String to convert was null");
            return true;
        }

        Matcher matcher = pattern.matcher(line);
        if(!matcher.find()){
            logger.error("string: " + line + " does not match");
            return true;
        }
        return false;
    }
}
