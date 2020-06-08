name := """crimes"""
organization := "p.lodz.pl"

version := "2.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(guice, evolutions, jdbc)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.5"
libraryDependencies += "com.h2database" % "h2" % "1.4.199"

