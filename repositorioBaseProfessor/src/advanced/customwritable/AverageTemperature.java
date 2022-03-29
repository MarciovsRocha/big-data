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

        j.setJarByClass(AverageTemperature.class);           // define a classe principal
        j.setMapperClass(MapForAverage.class);               // define a classe que contem o mapper
        j.setCombinerClass(CombineForAverage.class);         // define a classe que contem o combiner
        j.setReducerClass(ReduceForAverage.class);           // define a classe que contem o reducer
        j.setMapOutputKeyClass(Text.class);                  // define a classe da chave de output do mapper
        j.setMapOutputValueClass(FireAvgTempWritable.class); // define a classe do valor de output do mapper
        j.setOutputKeyClass(Text.class);                     // define a classe da chave de output do reducer
        j.setOutputValueClass(DoubleWritable.class);         // define a classe do valor de output do reducer

        FileInputFormat.addInputPath(j, input);              // define o caminho de input (localização do arquivo)
        FileOutputFormat.setOutputPath(j, output);           // define o caminho de output (localização de exportação)

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

    public static class CombineForAverage extends Reducer<Text, FireAvgTempWritable, Text, FireAvgTempWritable>{
        public void reduce(Text key, Iterable<FireAvgTempWritable> values, Context con)
                throws IOException, InterruptedException{
            // o objetivo
            int totalN=0;
            double totalSoma=0.0;
            for (FireAvgTempWritable o : values){
                totalN += o.getN();
                totalSoma += o.getSum();
            }
            // enviando do combiner para o sort/shuffle
            con.write(key, new FireAvgTempWritable(totalN,totalSoma));
        }
    }

    public static class ReduceForAverage extends Reducer<Text, FireAvgTempWritable, Text, DoubleWritable> {
        public void reduce(Text key, Iterable<FireAvgTempWritable> values, Context con)
                throws IOException, InterruptedException {

            int nTotal = 0;
            double somaTotal = 0.0;
            for (FireAvgTempWritable o : values){
                nTotal += o.getN();
                somaTotal += o.getSum();
            }
            double media = somaTotal/nTotal;
            con.write(key,new DoubleWritable(media));

        }
    }

}
