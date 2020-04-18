package com.Deltalake.Source

import com.Deltalake.Listeners.JobMetricsInfuxDBListener
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.streaming.scheduler

object SparkStreaming extends App{

  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark Delta Lake Example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    val salesData = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "sales_middle_east")
      .load()

    val query = salesData.writeStream
      .outputMode("Append")
      .format("console")
      .trigger(Trigger.ProcessingTime(30000))
      .start()

    query.awaitTermination()

   spark.streams.addListener(new JobMetricsInfuxDBListener)






}
