package com.Deltalake.Persistance

import com.redis._
import org.apache.log4j.Logger

class RedisCheckpoint() {
  val logger = Logger.getLogger("RedisCheckpoint")
  var client: RedisClient = _

  def persist(key: String, value: String): Boolean = {
    this.logger.debug(s"Persisting: ${value} to ${key}")
    this.client.set(key, value)
  }

  def persistBulk(keyValPairs: Map[String, String]): Unit = {
    for ((key, value) <- keyValPairs) this.persist(key, value)
  }

  def isExist(key: String): Boolean = this.client.exists(key)

  def retrieve(key: String): String = {
    this.logger.info(s"Retrieving key: ${key}")
    val attempt = this.client.get(key)
    attempt match {
      case Some(value) => value
      case _ => null
    }
  }

  def retrieveAsBulk(keys: List[String]): Map[String,String] = {
    var mapped = Map[String,String]()
    for(eachKey <- keys) mapped += (eachKey -> this.retrieve(eachKey))
    mapped
  }

  def this(url: String, port: Int) = {
    this
    println(s"Using REDIS ${url} : ${port}")
    this.client = new RedisClient(url, port)
  }
}




