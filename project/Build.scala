import sbt._
import Keys._


object ApplicationBuild extends Build {
	val appName = "image-util"
	val appVersion = "1.0.0"
	val appOrganization = "com.github.mt_sito"
	val buildScalaVersion = "2.10.3"

	lazy val root = Project(id = appName,
		base = file("."),
		settings = Project.defaultSettings ++ Seq(
			name := appName,
			organization := appOrganization,
			version := appVersion,
			scalaVersion := buildScalaVersion,

			libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
		))
}
