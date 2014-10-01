name := "freeveggie-play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(                                                                                                                                                                                              
  "org.mdubois" % "freeveggie-framework-core" % "1.0-SNAPSHOT",                                                                                                                                                                                                               
  "org.mdubois" % "freeveggie-service-api" % "1.0-SNAPSHOT"                                                                                                                                                                     
)

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typ/"
