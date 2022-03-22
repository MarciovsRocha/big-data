package advanced.customwritable;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class FireAvgTempWritable  implements WritableComparable<FireAvgTempWritable>{
    /*
     * Todo writable necessita ser um Java BEAN!
     *  1. Construtor Vazio - ok
     *  2. Getters Setters - ok
     *  3. Comparação entre objetos - ok
     *  4. Atributos devem ser privados - ok
     */

    private int n;
    private double sum;

    public FireAvgTempWritable(int n, double sum) {
        this.n = n;
        this.sum = sum;
    }

    public FireAvgTempWritable(){}

    public IntWritable getN() {
        return new IntWritable(n);
    }

    public DoubleWritable getSum() {
        return new DoubleWritable(sum);
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.n);
        dataOutput.writeDouble(this.sum);
    }

    // CUIDADO COM A ORDEM DE WRITE OUTPUT e READ INPUT
    // TODOS OS ELEMENTOS DEVEM ESTAR NA MESMA ORDEM
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.n = dataInput.readInt();
        this.sum = dataInput.readDouble();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireAvgTempWritable that = (FireAvgTempWritable) o;
        return n == that.n && Double.compare(that.sum, sum) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, sum);
    }

    @Override
    public int compareTo(FireAvgTempWritable fireAvgTempWritable) {
        if (this.hashCode() > fireAvgTempWritable.hashCode()){
            return +1;
        }else if (this.hashCode() < fireAvgTempWritable.hashCode()){
            return -1;
        }
        return 0;
    }
}
