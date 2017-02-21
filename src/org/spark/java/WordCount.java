package org.java;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCount {

	public static void main(String[] args) {
		SparkConf conf=new SparkConf().setAppName("WordCount");
		conf.setMaster("local");
		JavaSparkContext jsc=new JavaSparkContext(conf);
		
		String in="file/in";
		String out="file/outfile";
		JavaRDD<String> input=jsc.textFile(in);
		JavaRDD<String> words=input.flatMap(
				new FlatMapFunction<String,String>(){
					@Override
					public Iterable<String> call(String arg) throws Exception {
						return Arrays.asList(arg.split(" "));
					}
				}
		);
		
		JavaPairRDD<String,Integer> counts=words.mapToPair(
				new PairFunction<String,String,Integer>(){
					@Override
					public Tuple2<String, Integer> call(String x) throws Exception {
						return new Tuple2(x,1);
					}
				}
		).reduceByKey(
				new Function2<Integer, Integer, Integer>(){
					@Override
					public Integer call(Integer x, Integer y) throws Exception {
						return x+y;
					}
				}
		);
		
		//counts.saveAsTextFile(out);
		
		 List<Tuple2<String, Integer>> output = counts.collect();
		    for (Tuple2<?,?> tuple : output) {
		      System.out.println(tuple._1() + ": " + tuple._2());
		    }
		    jsc.stop();
	}

}
