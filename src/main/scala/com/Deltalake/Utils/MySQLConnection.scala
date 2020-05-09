//package com.Deltalake.Utils
//import java.sql.{Connection, DriverManager}
//import java.util.Properties
//
//import org.apache.commons.pool2.impl.{DefaultPooledObject, GenericObjectPool}
//import org.apache.commons.pool2.{BasePooledObjectFactory, PooledObject}
//
////import org.apache.logging.log4j.scala.Logging
////import org.apache.logging.log4j.Level
//
//
//
//
//class ConnectionFactory(jdbc: String) extends BasePooledObjectFactory[Connection] {
//
//  def create(): Connection = {
//    DriverManager.getConnection(jdbc)
//  }
//
//  def wrap(connection: Connection): PooledObject[Connection] = {
//    new DefaultPooledObject[Connection](connection)
//  }
//
//}
//
//
//case class ConnectionPool(jdbc: String) {
//
////  logger.info(s"Generating connection pool for: ${jdbc}")
//  val connectionPool = new GenericObjectPool[Connection](new ConnectionFactory(jdbc))
//
//  def getConnection: Connection = {
//    connectionPool.borrowObject
//  }
//
//  def returnConnection(connection: Connection): Unit = {
//    connectionPool.returnObject(connection)
//  }
//
//}
//
//case class MySQLConnection(
//                            host: String,
//                            port: Option[Int] = Option(3306), // scalastyle:off magic.number
//                            database: String,
//                            table: String,
//                            username: String,
//                            password: String,
//                            options: Map[String, String] = Map[String,String]()
//                          ) {
//  override def toString: String = {
//    s"jdbc:mysql://$host" +
//      (port match { case Some(p) => s":$p" case None => "" }) +
//      s"/$database?user=$username&password=$password" +
//      options.map(x => s"&${x._1}=${x._2}").mkString("")
//  }
//}
