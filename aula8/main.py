#!/usr/bin/python
import pyspark
from pyspark.sql import SparkSession
from pyspark import SparkFiles
spark = SparkSession.builder.master('local[*]').appName('MYAPP').getOrCreate()
sc = spark.sparkContext

# map file to spark
sc.addFile('https://www.dropbox.com/s/yviaftku1t09ejx/bible.txt?dl=1')

# load file
rdd = sc.textFile('file://'+ SparkFiles.get('bible.txt'))

palavras = rdd.flatMap(lambda frase: frase.split(' '))

palavras.take(10)