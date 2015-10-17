package DataProvider;

/**
 * Created by Lino on 19/08/2014.
 */
public class SingleRead {
    private Integer temp1 = null;
    private Integer temp2 = null;
    private Integer temp3 = null;
    private Integer temp4 = null;
    private Double pressure = null;
    private Double volt = null;
    private Double ampere = null;

    public Integer getTemp1() {
        return temp1;
    }

    public void setTemp1(Integer temp1) {
        this.temp1 = temp1;
    }

    public Integer getTemp2() {
        return temp2;
    }

    public void setTemp2(Integer temp2) {
        this.temp2 = temp2;
    }

    public Integer getTemp3() {
        return temp3;
    }

    public void setTemp3(Integer temp3) {
        this.temp3 = temp3;
    }

    public Integer getTemp4() {
        return temp4;
    }

    public void setTemp4(Integer temp4) {
        this.temp4 = temp4;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getVolt() {
        return volt;
    }

    public void setVolt(Double volt) {
        this.volt = volt;
    }

    public Double getAmpere() {
        return ampere;
    }

    public void setAmpere(Double ampere) {
        this.ampere = ampere;
    }

    public Integer getMilliVolt(){
        Double mesurament = volt;
        return convertIntoMilli(mesurament);
    }
    public Integer getMilliBar(){
        Double mesurament = pressure;
        return convertIntoMilli(mesurament);
    }

    public Integer getMilliAmpere(){
        return convertIntoMilli(ampere);
    }

    public Double getWatt(){
        return calculatePower();
    }


    private Double calculatePower() {
        if(ampere != null && volt != null)
            return (ampere* volt);
        else
            return null;
    }

    public Integer getMilliWatt(){
        return convertIntoMilli(calculatePower());
    }

    private Integer convertIntoMilli(Double mesurament) {
        if(mesurament!= null)
            return new Integer((int)(mesurament* (double)1000));
        else
            return null;
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
        return result;
    }
}
