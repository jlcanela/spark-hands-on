ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.example"

lazy val handsOn = (project in file("."))
  .settings(
    name := "SparkHandsOn",
	libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0",  
	libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0",  
	libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.8",  
	libraryDependencies += "com.github.azakordonets" %% "fabricator" % "2.1.5",
	libraryDependencies += "joda-time" % "joda-time" % "2.10.1"
  )
