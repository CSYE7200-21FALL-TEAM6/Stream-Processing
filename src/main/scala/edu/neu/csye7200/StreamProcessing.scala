package edu.neu.csye7200

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*

 */
object StreamProcessing {
  def main(args: Array[String]): Unit = {

    // create StreamingContext
    val conf = new SparkConf().setMaster("local[2]").setAppName("TwitterStreaming")
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
    val topics = Array("hello")

    // consume streaming data from Kafka
    val kafkaDStream = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics, kafkaParams)
    )

    // convert streaming data to RDD
    kafkaDStream.foreachRDD(rdd =>{
    })

  }
}
