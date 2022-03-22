package advanced.customwritable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.Iterator;

public class AverageTemperature {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        // arquivo de entrada
        Path input = new Path(files[0]);

        // arquivo de saida
        Path output = new Path(files[1]);

        // criacao do job e seu nome
        Job j = new Job(c, "media");

        // lanca o job e aguarda sua execucao
        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }


    public static class MapForAverage extends Mapper<LongWritable, Text, Text, FireAvgTempWritable> {

        // Funcao de map
        public void map(LongWritable key, Text value, Context con)
                throws IOException, InterruptedException {
            // value é a linha completa, transformo em string e separo as colunas pela vírgula
            // index 8 é a coluna desejada
            double temperatura = Double.parseDouble(value.toString().split(",")[8]);
            // emitir chave e valor -> criada classe customizada para armazenar os valores
            // chave "media"
            // valor (n=1,sum=temperatura)
            FireAvgTempWritable obj = new FireAvgTempWritable(1, temperatura);
            con.write(new Text("media"), obj);
        }
    }

    public static class ReduceForAverage extends Reducer<Text, FireAvgTempWritable, Text, FloatWritable> {
        public void reduce(Text key, Iterable<FireAvgTempWritable> values, Context con)
                throws IOException, InterruptedException {
            // recebe chave e lista de valores
            // (chave="media, [...])
            // cada valor é um objeti (n,soma)
            // somar os N's
            // somar as somas
            // media somas/N's
        }
    }

}
