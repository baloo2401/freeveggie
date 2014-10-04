name := "freeveggie-play"

version := "1.0-SNAPSHOT"

resolvers += "Nexus snapshot repository" at "http://nexus.edubois.org/nexus/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "org.mdubois" % "freeveggie-service-api" % "1.0-SNAPSHOT",
  "org.mdubois" % "freeveggie-framework-core" % "1.0-SNAPSHOT"
)
