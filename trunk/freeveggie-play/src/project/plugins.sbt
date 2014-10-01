// Comment to get more information during initialization
logLevel := Level.Warn

//Dependencies
libraryDependencies += "org.mdubois" % "freeveggie-framework-core" % "1.0-SNAPSHOT"
libraryDependencies += "org.mdubois" % "freeveggie-service-api" % "1.0-SNAPSHOT"

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.0")