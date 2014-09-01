package DataProvider;

/**
 * Created by Lino on 19/08/2014.
 */
public class SingleRead {
    private int temp1;
    private int temp2;
    private int temp3;
    private int temp4;
    private double pressure;
    private double volt;
    private double ampere;

    public int getTemp1() {
        return temp1;
    }

    public void setTemp1(int temp1) {
        this.temp1 = temp1;
    }

    public int getTemp2() {
        return temp2;
    }

    public void setTemp2(int temp2) {
        this.temp2 = temp2;
    }

    public int getTemp3() {
        return temp3;
    }

    public void setTemp3(int temp3) {
        this.temp3 = temp3;
    }

    public int getTemp4() {
        return temp4;
    }

    public void setTemp4(int temp4) {
        this.temp4 = temp4;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getVolt() {
        return volt;
    }

    public int getMilliVolt(){
        return (int)(volt* (double)1000);
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public double getAmpere() {
        return ampere;
    }

    public int getMilliAmpere(){
        return (int)(ampere* (double)1000);
    }

    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    public double getWatt(){
        return (ampere* volt);
    }

    public int getMilliWatt(){
        return (int)((ampere* volt)* (double) 1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleRead)) return false;

        SingleRead that = (SingleRead) o;

        if (ampere != that.getAmpere()) return false;
        if (pressure != that.getPressure()) return false;
        if (temp1 != that.getTemp1()) return false;
        if (temp2 != that.getTemp2()) return false;
        if (temp3 != that.getTemp3()) return false;
        if (temp4 != that.getTemp4()) return false;
        if (volt != that.getVolt()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = temp1;
        result = 31 * result + temp2;
        result = 31 * result + temp3;
        result = 31 * result + temp4;
        result = 31 * result + (int)pressure;
        result = 31 * result + (int)volt;
        result = 31 * result + (int)ampere;
        return result;
    }
}
