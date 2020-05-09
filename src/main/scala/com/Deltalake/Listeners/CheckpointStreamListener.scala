package com.Deltalake.Listeners
import com.Deltalake.Persistance.RedisCheckpoint
import org.apache.log4j.Logger
import org.apache.spark.sql.streaming.StreamingQueryListener
import org.json._



class CheckpointStreamListener(implicit redisCheckpoint: RedisCheckpoint) extends StreamingQueryListener {
  val logger = Logger.getLogger("CheckpointStreamListener")

  override def onQueryStarted(event: StreamingQueryListener.QueryStartedEvent): Unit =
    this.logger.debug(s"onQueryStarted : ${event.id}, name: ${event.name},runId : ${event.runId}")

  override def onQueryProgress(event: StreamingQueryListener.QueryProgressEvent): Unit = {
    // Stores end offsets after each microbatch execution has been fulfilled
    val eventProgressObj = new JSONObject(event.progress.json)
    val sourceJson = eventProgressObj.getJSONArray("sources").get(0).asInstanceOf[JSONObject]
    val endOffsetsJson = sourceJson.getJSONObject("endOffset")
    var mappedKeysToValue = Map[String,String]()
    val itr = endOffsetsJson.keys

    while(itr.hasNext){
      val key: String = itr.next
      mappedKeysToValue += (key -> endOffsetsJson.get(key).toString)
    }
    this.logger.debug(s"Checkpoint listener saving offsets: ${endOffsetsJson.toString}")
    redisCheckpoint.persistBulk(mappedKeysToValue)
  }

  override def onQueryTerminated(event: StreamingQueryListener.QueryTerminatedEvent): Unit =
    this.logger.debug(s"onQueryTerminated : ${event.exception},id : ${event.id}, runId: ${event.runId}")
}


