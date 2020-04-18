package com.Deltalake.Kafka

import java.util.Properties
import org.apache.kafka.clients.producer._
import scala.io.Source

object Producer {

  def main(args: Array[String]): Unit = {
    writeToKafka("sales_middle_east")
  }

  def writeToKafka(topic: String): Unit = {


    val data = "/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/destination/Country=Afghanistan/part-00000-b149ff20-ddc1-445d-90f4-c6e31d1e6181.c000.json"
    val fSource = Source.fromFile(data)
    var key = 0
    for(line<-fSource.getLines)
    {

      val paylod = line
      val props = new Properties()
      props.put("bootstrap.servers", "localhost:9092")
      props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      val producer = new KafkaProducer[String, String](props)
      println(s"The value of key is $key")
      println(s"The value of record is $paylod")
      val record = new ProducerRecord[String, String](topic, null, paylod)
      producer.send(record)
      producer.close()
      key = key +1
      Thread.sleep(30000)
    }

  }
}