name := """crimes"""
organization := "p.lodz.pl"

version := "2.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(guice, evolutions, jdbc, ws)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.5"
libraryDependencies += "com.h2database" % "h2" % "1.4.199"
libraryDependencies += "com.typesafe.play" % "play-iteratees_2.11" % "2.5.18"
libraryDependencies += "org.webjars" % "flot" % "0.8.3-1"
libraryDependencies += "org.webjars" % "bootstrap" % "3.3.7"
libraryDependencies += "org.awaitility" % "awaitility" % "4.0.1" % Test

