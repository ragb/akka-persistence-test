name := "akka-persistence-test"

version := "0.1-SNAPHOST"

organization := "com.ruiandrebatista.testing"

scalaVersion := "2.11.7"

libraryDependencies ++= {
	val akkaV = "2.4.0-RC2"
	Seq("com.typesafe.akka" %% "akka-actor" % akkaV,
	"com.typesafe.akka" %% "akka-persistence" % akkaV,
	"org.iq80.leveldb"            % "leveldb"          % "0.7",
	"org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8")
}

//fork in run := true


