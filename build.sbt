name := "ScalaDroolsDummyProject"

version := "4"

scalaVersion := "2.11.8"

mainClass in assembly := Some("dummy.Dummy")

assemblyJarName in assembly := "dummy.jar"


scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xexperimental",
  "-feature",
  "-language:implicitConversions",
  "-language:reflectiveCalls"
)



libraryDependencies ++= Seq(
  //Drools
  "drools-compiler",
  "drools-core",
  "drools-jsr94",
  "drools-decisiontables",
  "knowledge-api"
).map("org.drools" % _ % "6.4.0.Final")

lazy val json4sVersion = "3.3.0"

libraryDependencies ++= Seq(
  //Akka and Akka Http
  "com.typesafe.akka" %% "akka-actor" % "2.4.7",
  "com.typesafe.akka" %% "akka-http-core" % "2.4.7",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.7",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.7",
  "com.typesafe.akka" %% "akka-http-jackson-experimental" % "2.4.7",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.7",

  //Json
  "org.json4s" %% "json4s-core" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.json4s" %% "json4s-native" % json4sVersion,
  "de.heikoseeberger" %% "akka-http-json4s" % "1.4.1",


  "ch.qos.logback"           % "logback-classic"   % "1.1.7",
  "org.codehaus.janino"      % "janino"            % "2.5.16",   // For drools

  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "junit"          % "junit"     % "4.12"   % "test"
)

libraryDependencies ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.5",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4")
    case _ =>
      libraryDependencies.value
  }
}


initialCommands in console := """import dummy._"""

resolvers += "jboss-releases" at "https://repository.jboss.org/nexus/content/repositories/releases"

resolvers += "jboss-jsr94" at "http://repository.jboss.org/nexus/content/groups/public-jboss"

resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"
