package advanced.customwritable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ForestFireWritable {

    private double temp, wind;

    public double getTemp() {
        return temp;
    }

    public double getWind() {
        return wind;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }
}
