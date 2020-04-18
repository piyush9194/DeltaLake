//package com.Deltalake.Checkpointing
//import org.apache.spark.streaming.kafka010.OffsetRange
//import org.apache.spark.streaming.scheduler._
//import org.joda.time.DateTime
//import com.Deltalake.Utils.MySQLConnection
//
///**
//  *
//
//  * @param mySQLHost         Hostname of the MySQL service
//  * @param mySQLDB           Database name in MySQL to write to
//  * @param mySQLTable        Table name in MySQL to write to
//  * @param mySQLUser         Username for authentication in MySQL
//  * @param mySQLPwd          Password for authentication in MySQL
//  * @param mySQLConsumer     Unique name for tracking offsets across streaming apps
//  */
//
//class MysqlCheckpointing  (mySQLHost: String,
//                           mySQLDB: String,
//                           mySQLTable: String,
//                           mySQLConsumer: String,
//                           mySQLUser: String,
//                           mySQLPwd: String) extends StreamingListener{
//
//
//  @transient lazy val mySQLConnectionPool = MySQLConnection(host = mySQLHost,
//                                                            table = mySQLTable,
//                                                            username = mySQLUser,
//                                                            password = mySQLPwd,
//                                                            database = mySQLDB)
//
//  /**
//    * Takes a topic object and writes the max offset for each partition it contains this batch to MySQL.
//    *
//    * @param topic A topic object within a Batch's StreamIdToInputInfo
//    */
//  def writeTopicOffsetsToMySQL(topic: Tuple2[Int, StreamInputInfo]): Unit = {
//
//    // map offset info to OffsetRange objects
//    val partitionOffsets = topic._2.metadata("offsets").asInstanceOf[List[OffsetRange]]
//
//    // for every partition's range of offsets
//    partitionOffsets.map(offsetRange => {
//
//      // write the new starting offset for each partition in the topic to the state db
//      var maxOffset = offsetRange.untilOffset - 1
//
//      // create a now() timestamp
//      val now = new DateTime().toString("YYYY-MM-dd HH:mm:ss")
//
//      // form the sql
//      val sql =
//        s"""INSERT INTO $mySQLDB.$mySQLTable (consumer, topic, partition_id, offset, offset_ts, batch_size)
//          VALUES
//          ('$mySQLConsumer', "${offsetRange.topic}", ${offsetRange.partition}, '$maxOffset', '$now', ${offsetRange.count})
//          ON DUPLICATE KEY UPDATE offset_ts = VALUES(offset_ts), offset = VALUES(offset),
//            batch_size = VALUES(batch_size)
//        """
//
//      // execute the sql to offload offsets to the table
//      val st = mySQLConnectionPool.createStatement
//      st.execute(sql)
//      st.close()
//    })
//  }
//
//  /**
//    * Takes a topic object and writes the number of records for said topic this batch to Influx.
//    *
//    * @param topic A topic object within a Batch's StreamIdToInputInfo
//    */
//  def writeTopicCountToInflux(topic: Tuple2[Int, StreamInputInfo]): Unit = {
//
//    // store the individual record count for this topic
//    val numRecords = topic._2.numRecords
//
//    // store topicName
//    val topicName = topic._2.metadata("offsets").asInstanceOf[List[OffsetRange]].head.topic
//
//    // write record count for this topic this batch
//    influx.write(influxDB, influxMeasurement, Seq(), Seq(("numRecords_" + topicName, numRecords)))
//  }
//
//}
