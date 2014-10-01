name := "freeveggie-play"

version := "1.0-SNAPSHOT"

resolvers += (
    "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"
)

libraryDependencies ++= Seq(                                                                                                                                                                                              
  "org.mdubois" % "freeveggie-framework-core" % "1.0-SNAPSHOT",                                                                                                                                                                                                               
  "org.mdubois" % "freeveggie-service-api" % "1.0-SNAPSHOT"                                                                                                                                                                     
)
