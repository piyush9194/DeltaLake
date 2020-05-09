package com.Deltalake.KafkaProducer

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.codegen.FalseLiteral
import org.apache.spark.sql.types.{IntegerType, TimestampType,StructType}
import org.apache.spark.sql.functions._


object Producer {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  def main(args: Array[String]):Unit={
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Spark Delta Lake Example")
      .config("spark.executor.instances", "4")
//      .config("spark.executor.cores", "1")
      .config("spark.executor.memory", "1g")
      .config("spark.metrics.conf.driver.sink.graphite.class","org.apache.spark.metrics.sink.GraphiteSink")
      .config("spark.metrics.conf.executor.sink.graphite.class","org.apache.spark.metrics.sink.GraphiteSink")
      .config( "spark.metrics.conf.*.sink.graphite.host","http://localhost")
      .config("spark.metrics.conf.*.sink.graphite.port","2003")
      .config("spark.metrics.conf.*.sink.graphite.period","10")
      .config("spark.metrics.conf.*.sink.graphite.unit","seconds")
      .config("spark.metrics.conf.*.sink.graphite.prefix","lucatest")
      .config("spark.metrics.conf.*.source.jvm.class","org.apache.spark.metrics.source.JvmSource")
      .getOrCreate()


    // Simple Hello World output (performed on the driver
    println("Hello, Scala!")

    val userSchema = new StructType()
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


//    var loc = "/Users/piyush.gupta/DE_CodeBase/Coding_Practice/covid-19/data/covid/us_confirmed.csv"
//    var loc = "/Users/piyush.gupta/DE_CodeBase/Coding_Practice/covid-19/data/covid/"
//    var data = spark.read
//                    .format("csv")
//                    .option("inferSchema",true)
//                    .option("header",true)
//                    .load(loc)


    var data = spark
      .readStream
      .schema(userSchema)      // Specify schema of the csv files
      .format("csv")
      .option("header", "true")
      .load(path="/Users/piyush.gupta/DE_CodeBase/Coding_Practice/covid-19/data/covid/new/")

    data.printSchema()

//

    val destination = "/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/destination"
//    data.write.csv(destination,header ='true')



    val query = data.selectExpr("CAST(UID AS STRING) AS key", "to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .outputMode("append")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "covid1")
      .option("checkpointLocation", "/Users/piyush.gupta/DE_CodeBase/DeltaLake/src/main/destination/covid1")
      .start()

      query.awaitTermination()


//    val delta_read = spark.read
//                     .format("delta")
//      .load("/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/destination/Country=Afghanistan")
//
//    delta_read.show(10,false)
  }
}
