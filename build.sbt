name := "project_ak"

version := "1.0"

scalaVersion := "2.12.2"

mainClass in(Compile, run) := Some("start.start")

fork := true

javacOptions ++= Seq("-encoding", "UTF-8")
javaOptions += "-noverify"
lazy val switchTask = taskKey[String]("Switch Jrebel By Platform !")
switchTask := System.getProperty("os.name").toUpperCase
javaOptions += {
    println(s"switchTask.value : ${switchTask.value}")
    if (switchTask.value.contains("WINDOWS"))
        "-agentpath:" + baseDirectory.value / "jrebel64.dll"
    else "-agentpath:" + baseDirectory.value / "libjrebel64.so"
}
javaOptions += ("-Drebel.dirs=" + target.value / "scala-2.12/classes")
javaOptions += "-Drebel.disable_update=true"
javaOptions += "-Xms512m"
javaOptions += "-Xmx512m"
javaOptions += "-XX:+UseG1GC"
javaOptions += "-XX:MaxGCPauseMillis=100"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.12" % "2.5.6"
libraryDependencies += "com.typesafe.akka" % "akka-cluster_2.12" % "2.5.6"
libraryDependencies += "com.typesafe.akka" % "akka-http_2.12" % "10.0.10"
