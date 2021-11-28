package edu.neu.csye7200.twitterproject.sparksample

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)

    val linesRDD = sc.textFile("src/main/resources/hello.txt")

    val res = linesRDD.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)

    res.foreach(tuple => println(tuple._1 + "--" + tuple._2))

    sc.stop()
  }
}
