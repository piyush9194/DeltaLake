name := "DeltaLakeDemo"
version := "0.1"
scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  var sparkVersion = "2.4.4"
  Seq(
  "org.apache.kafka" %% "kafka" % "2.1.0" ,
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-streaming" % sparkVersion,
    "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
    "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-hive" % sparkVersion,
    "com.typesafe" % "config" % "1.2.1",
    "net.debasishg" %% "redisclient" % "3.8",
    //    "org.json4s" %% "json4s-jackson" % "3.2.11" % "provided",
    "org.apache.commons" % "commons-lang3" % "3.7",
    "com.amazonaws" % "aws-java-sdk" % "1.10.77",
    "org.json" % "json" % "20180130",
    "io.delta" %% "delta-core" % "0.5.0",
    "mysql" % "mysql-connector-java" % "8.0.18",
   "org.apache.commons" % "commons-pool2" % "2.0",
//    "com.lihaoyi" %% "upickle" % "0.7.1",
//    "io.druid" %% "tranquility-spark" % "0.7.3",
//    "ch.cern.sparkmeasure" %% "spark-measure" % "0.15"

    //    "org.apache.logging.log4j" %% "log4j-api-scala" % "11.1",
//    "org.apache.logging.log4j" % "log4j-core" % "2.11.8"
  )
}

dependencyOverrides +=  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.7"
dependencyOverrides +=  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.7"



assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}