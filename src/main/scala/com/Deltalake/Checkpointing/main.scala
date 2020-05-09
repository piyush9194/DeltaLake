package com.Deltalake.Checkpointing

import com.Deltalake.Listeners.CheckpointStreamListener
import com.Deltalake.Persistance.RedisCheckpoint
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{StreamingQuery, StreamingQueryException, Trigger}
import org.apache.spark.sql.types.StructType


object Batch extends App{
  val logger = Logger.getLogger("DeltaLake")

  val topic = "covid2"


  val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("LoadBatchData")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

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


  val cases = spark
          .read
          .option("header", "true")
    .csv("/Users/piyush.gupta/DE_CodeBase/DeltaLake/src/main/data/coronavirusdataset/Case.csv")



  val patient_info = spark
    .read
    .option("header", "true")
    .csv("/Users/piyush.gupta/DE_CodeBase/DeltaLake/src/main/data/coronavirusdataset/PatientInfo.csv")

  cases.show(10)
  patient_info.show(10)


}
