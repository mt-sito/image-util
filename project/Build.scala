import sbt._
import Keys._


object ApplicationBuild extends Build {
	val appName = "image-util"
	val appVersion = "1.1.1"
	val appOrganization = "com.github.mt_sito"
	val buildScalaVersion = "2.11.7"

	lazy val root = Project(id = appName,
		base = file("."),
		settings = Project.defaultSettings ++ Seq(
			name := appName,
			organization := appOrganization,
			version := appVersion,
			scalaVersion := buildScalaVersion,
			publishMavenStyle := true,
			otherResolvers := Seq(Resolver.file("dotM2", file(Path.userHome + "/.m2/repository"))),
			publishLocalConfiguration <<= (packagedArtifacts, deliverLocal, ivyLoggingLevel) map {
				(arts, _, level) => new PublishConfiguration(None, "dotM2", arts, List[String]("sha1", "md5"), level)
			},

			crossScalaVersions := Seq(
				"2.10.4",
				"2.11.7"
			),

			libraryDependencies <+= scalaVersion(v => v match {
				case "2.10.4" => "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
				case _ => "org.scalatest" %% "scalatest" % "2.2.2" % "test"
			})
		)
	)
}
