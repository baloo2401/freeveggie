name := "freeveggie-play"                                                                                                                                                                                                

version := "1.0-SNAPSHOT"                                                                                                                                                                                                 

libraryDependencies ++= Seq(                                                                                                                                                                                              
  javaJdbc,                                                                                                                                                                                                               
  javaEbean,                                                                                                                                                                                                              
  cache                                                                                                                                                                                                                   
)

//Dependencies
libraryDependencies += "org.mdubois" % "freeveggie-framework-core" % version
libraryDependencies += "org.mdubois" % "freeveggie-service-api" % "version

play.Project.playJavaSettings