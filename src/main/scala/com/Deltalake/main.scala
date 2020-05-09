package com.Deltalake

import com.Deltalake.Listeners.CheckpointStreamListener
import com.Deltalake.Persistance.RedisCheckpoint
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{StreamingQuery, StreamingQueryException, Trigger}
import org.apache.spark.sql.types.StructType
import org.apache.kafka.common.serialization.StringDeserializer


object main extends App{
  val logger = Logger.getLogger("DeltaLake")

  val topic = "covid2"


  implicit val redisCheckpoint: RedisCheckpoint = new RedisCheckpoint("localhost", 6379)

  val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("DataPump")
      .config("spark.some.config.option", "some-value")
    .config("spark.streaming.stopGracefullyOnShutdown","true")
      .getOrCreate()

  val covidSchema = new StructType()
                    .add("UID", "string")
                    .add("iso2", "string")
                    .add("iso3", "string")
                    .add("code3", "string")
                    .add("FIPS", "string")
                    .add("Admin2", "string")
                    .add("Lat", "string")
                    .add("Combined_Key", "string")
                    .add("Date", "string")
                    .add("Case", "string")
                    .add("Long", "string")
                    .add("Country/Region", "string")
                    .add("Province/State", "string")

    val covidDataDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", topic)
      .option("startingOffsets", "earliest")
//      .option("enable.auto.commit", "true")
      .option("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
//      .option("isolation.level", "read_committed")
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")


  covidDataDF.printSchema()

  val query: StreamingQuery = covidDataDF
    .select( col("key").cast("string"), from_json(col("value").cast("string"),covidSchema).alias("data"))
     .select("data.*")
      .writeStream
      .queryName("StreamingApplication")
      .outputMode("Append")
      .format("csv")
      .trigger(Trigger.ProcessingTime(30000))
      .option("checkpointLocation", s"hdfs://localhost:9000/user/Hadoop/sparkstreaming/${topic}")
      .start(s"hdfs://localhost:9000/user/Hadoop/sparkstreaming/covid_data/${topic}");




  spark.streams.addListener(new CheckpointStreamListener)

//   spark.streams.addListener(new JobMetricsInfuxDBListener)


  try {
    // Await termination of streaming query
    query.awaitTermination()
    logger.info("Application set to terminate. Good bye world -_-")
  } catch {
    case ex: StreamingQueryException => {
      logger.error(ex.toString)
      throw ex
    }
  }
}
