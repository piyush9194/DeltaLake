package com.Deltalake.BatchDelta

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.codegen.FalseLiteral
import org.apache.spark.sql.types.{IntegerType, TimestampType}
import org.apache.spark.sql.functions._


object DeltaLake {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  def main(args: Array[String]):Unit={
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark Delta Lake Example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    // Simple Hello World output (performed on the driver
    println("Hello, Scala!")

    val sales_Data = "/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/resources/data.csv"
    var data = spark.read.format("csv")
              .option("inferSchema", "true")
              .option("header", "true")
              .load(sales_Data)

    data.printSchema()



    data.show(10,false)
//

    val destination = "/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/destination"
//    data.write.csv(destination,header ='true')



//
//    data = data.limit(100)
//    data
//      .writeStream
//      .format("kafka")
//      .outputMode("append")
//      .option("kafka.bootstrap.servers", "localhost:9092")
//      .option("topic", "sales")
//      .start()
//      .awaitTermination()


//    val delta_read = spark.read
//                     .format("delta")
//      .load("/Users/piyush.gupta/DE_CodeBase/DeltaLakeDemo/src/main/destination/Country=Afghanistan")
//
//    delta_read.show(10,false)
  }
}
