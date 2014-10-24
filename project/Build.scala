import sbt._
import Keys._


object ApplicationBuild extends Build {
	val appName = "image-util"
	val appVersion = "1.0.1"
	val appOrganization = "com.github.mt_sito"
	val buildScalaVersion = "2.11.2"

	lazy val root = Project(id = appName,
		base = file("."),
		settings = Project.defaultSettings ++ Seq(
			name := appName,
			organization := appOrganization,
			version := appVersion,
			scalaVersion := buildScalaVersion,

			crossScalaVersions := Seq(
				"2.10.4",
				"2.11.2"
			),

			libraryDependencies <+= scalaVersion(v => v match {
				case "2.10.4" => "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
				case _ => "org.scalatest" %% "scalatest" % "2.2.2" % "test"
			})
		)
	)
}
