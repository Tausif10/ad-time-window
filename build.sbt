parallelExecution in Test := false

name := "timeWindow"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.8.3" % Test,
  "org.specs2" %% "specs2-mock" % "4.8.0" % Test
)