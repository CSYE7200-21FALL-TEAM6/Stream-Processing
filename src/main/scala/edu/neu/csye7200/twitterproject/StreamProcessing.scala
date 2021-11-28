package edu.neu.csye7200.twitterproject

import edu.neu.csye7200.twitterproject.machinelearning.MLlibNaiveBayesPrediction
import edu.neu.csye7200.twitterproject.utils.{PropertiesLoader, StopWordsLoader}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.mllib.classification.NaiveBayesModel
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*

 */
object StreamProcessing {
  def main(args: Array[String]): Unit = {

    // create StreamingContext
//    val conf = new SparkConf().setMaster("local[2]").setAppName("Twitter_Stream_Processing")
    val conf = new SparkConf().setAppName("Twitter_Stream_Processing")
    val ssc = new StreamingContext(conf, Seconds(5))

    // Kafka params
    val kafkaParams = Map[String, Object](
      elems = "bootstrap.servers"->"localhost:9092",
      "key.deserializer"->classOf[StringDeserializer],
      "value.deserializer"->classOf[StringDeserializer],
      "group.id"->"con_1",
      "auto.offset.reset"->"latest",
      "enable.auto.commit"->(true: java.lang.Boolean)
    )
    // Kafka topics
    val topics = Array("twitterdata")

    // consume streaming data from Kafka
    val kafkaDStream = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics, kafkaParams)
    )

    val naiveBayesModel = NaiveBayesModel.load(ssc.sparkContext, PropertiesLoader.naiveBayesModelPath)
    val stopWordsList = ssc.sparkContext.broadcast(StopWordsLoader.loadStopWords(PropertiesLoader.nltkStopWordsFileName))
    // convert streaming data to RDD
    kafkaDStream.foreachRDD(rdd => {
      rdd.foreachPartition(it => {
        it.foreach(record => {
          println(record.value())
          val res = MLlibNaiveBayesPrediction.computeSentiment(record.value(), stopWordsList, naiveBayesModel)
          println(res)
          println("--------------------------------------------------")
        })
      })
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
