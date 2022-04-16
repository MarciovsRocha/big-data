package TDE01;

import com.google.common.collect.Lists;
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
import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();
        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c,args).getRemainingArgs();
        Path input = new Path(files[0]);
        //Path output = new Path(files[1]);

        // TesteExercicio(1, input, c);
        TesteExercicio(2, input, c);
    } // public static void

    public static void TesteExercicio(int IdExercicio, Path input, Configuration c) throws IOException, ClassNotFoundException, InterruptedException {
        Job j = new Job(c,"ApplicationMain");
        j.setJarByClass(ApplicationMain.class);
        FileInputFormat.addInputPath(j,input);
        switch (IdExercicio){
            case 1:
                j.setMapperClass(MapBrazilTransactions.class);
                j.setReducerClass(ReduceBrazilTransactions.class);
                j.setMapOutputKeyClass(Text.class);
                j.setMapOutputValueClass(IntWritable.class);
                j.setOutputKeyClass(Text.class);
                j.setOutputValueClass(IntWritable.class);
                FileOutputFormat.setOutputPath(j,new Path("output/TDE1/Exercicio_1"));
                j.waitForCompletion(false);
                break;
            case 2:
                j.setMapperClass(MapYearTransactions.class);
                j.setReducerClass(ReduceYearTransactions.class);
                j.setMapOutputKeyClass(Text.class);
                j.setMapOutputValueClass(IntWritable.class);
                j.setOutputKeyClass(Text.class);
                j.setOutputValueClass(IntWritable.class);
                FileInputFormat.addInputPath(j,input);
                FileOutputFormat.setOutputPath(j,new Path("output/TDE1/Exercicio_2"));
                j.waitForCompletion(false);
                break;
        }
    }

    /* ------------------------------------ [ EXERCICIO 1 ] ------------------------------------ */
    public static class MapBrazilTransactions extends Mapper<LongWritable, Text, Text, IntWritable>{
        public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
            String[] atributos = value.toString().split(";");
            con.write(new Text(atributos[0]), new IntWritable(1));
        }
    } // static class MapBrazilTransactions
    public static class ReduceBrazilTransactions extends Reducer<Text, IntWritable, Text, IntWritable>{
        public void reduce(Text key, Iterable<IntWritable> ocorrencias, Context con) throws IOException, InterruptedException{
            if (key.toString().equals("Brazil")){
                con.write(
                        new Text("Brazil Transactions: "),
                        new IntWritable(Lists.newArrayList(ocorrencias).size())
                );
            }
        }
    } // static class ReduceBrazilTransactions

    /* ------------------------------------ [ EXERCICIO 2 ] ------------------------------------ */
    public static class MapYearTransactions extends Mapper<LongWritable, Text, Text, IntWritable>{
        public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
            String[] atributos = value.toString().split(";");
            con.write(new Text(atributos[1]), new IntWritable(1));
        }
    } // static class MapYearTransactions
    public static class ReduceYearTransactions extends Reducer<Text, IntWritable, Text, IntWritable>{
        public void reduce(Text key, Iterable<IntWritable> ocorrencias, Context con) throws IOException, InterruptedException{
            con.write(
                    new Text("Transactions in "+key.toString()+": "),
                    new IntWritable(Lists.newArrayList(ocorrencias).size())
                );
        }
    } // static class ReduceYearTransactions
} // class ApplicationMain