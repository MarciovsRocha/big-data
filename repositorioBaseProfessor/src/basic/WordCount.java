package basic;

import java.io.IOException;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;


public class WordCount {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        // arquivo de entrada
        Path input = new Path(files[0]);

        // arquivo de saida
        Path output = new Path(files[1]);

        // criacao do job e seu nome
        Job j = new Job(c, "wordcount");

        // registro de classes (main,map,reduce)
        j.setJarByClass(WordCount.class);
        j.setMapperClass(MapForWordCount.class);
        j.setReducerClass(ReduceForWordCount.class);

        // tipos de saída (map e reduce)
        j.setMapOutputKeyClass(Text.class); // set output key class of map
        j.setMapOutputValueClass(IntWritable.class); // set output value class of map
        j.setOutputKeyClass(Text.class); // set output key of reduce
        j.setOutputValueClass(IntWritable.class); // set output value of reduce

        // Cadastro de arquivos de [In|out]put
        FileInputFormat.addInputPath(j,input);
        FileOutputFormat.setOutputPath(j, output);

        // executar a rotina
        j.waitForCompletion(false);

    }
    /*
     * 4 tipos de dados
     * 2 tipos de entrada
     *   - Chave (offset, mas ignoramos)
     *   - Valor (text pois cada linha do arquivo é uma string)
     * 2 tipos de saída
     * */
    public static class MapForWordCount extends Mapper<LongWritable, Text, Text, IntWritable> {

        public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException {
            String linha = value.toString();
            String[] palavras = linha.split(" ");

            for (String p : palavras){
                // passado chave-valor para reduce
                // implementar filtro de conteudo aqui...
                con.write(new Text(p), new IntWritable(1));
            }
        }
    }


    /*
    * 4 Tipos
    *   2 Tipos Chave e valor de Entrada !! IGUAIS AOS TIPOS DE SAIDA DO MAP
    *   2 Tipos Chave e valore de Saída  !! DEPENDE DA IMPLEMENTACO !!
    * */
    public static class ReduceForWordCount extends Reducer<Text, IntWritable, Text, IntWritable> {


        public void reduce(Text key, Iterable<IntWritable> values, Context con)
                throws IOException, InterruptedException {
            // a funcao de reduce será automaticamente chamada POR CHAVE

            // nessa implementação serão somados os valores da lista
            int soma = 0;
            for (IntWritable v : values){
                soma+=v.get();
            }
            // salvando em arquivo
            con.write(key,new IntWritable(soma));
        }
    }

}
