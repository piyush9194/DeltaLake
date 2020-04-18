package com.Deltalake.Listeners
//
//import org.apache.spark.Spark
//
//class BatchListenerUtils(
//                          measurementName: String,
//                          database: String) extends SparkListener {
//  override def onApplicationStart(applicationStart: SparkListenerApplicationStart): Unit = {
//    influxWriter.write(myTimeSeriesTS,
//      applicationStart.appName,
//      applicationStart.time)
//  }
//  override def onExecutorRemoved(executorRemoved: SparkListenerExecutorRemoved): Unit = {
//    influxWriter.write(myTimeSeriesTS,
//  } }
