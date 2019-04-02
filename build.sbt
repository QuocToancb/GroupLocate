name := """grouplocate"""
organization := "com.quoctoan"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.0-RC2"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"         % "3.3.2"
)

libraryDependencies ++= Seq(
  "com.h2database"  %  "h2"                           % "1.4.197", // your jdbc driver here
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.3.2",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.7.0-scalikejdbc-3.3"
)
