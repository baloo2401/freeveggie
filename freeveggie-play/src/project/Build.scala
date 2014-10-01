import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "freeveggie-play"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  ).settings(
    // Add custom repository: 
    resolvers += "Nexus snapshot repository" at "http://usine-dev.edubois.org/nexus/content/repositories/snapshots/"
  )

}
