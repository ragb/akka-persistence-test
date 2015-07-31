name := "akka-persistence-test"

version := "0.1-SNAPHOST"

organization := "com.ruiandrebatista.testing"

scalaVersion := "2.11.6"

libraryDependencies ++= {
	val akkaV = "2.3.12"
	Seq("com.typesafe.akka" %% "akka-actor" % akkaV,
	"com.typesafe.akka" %% "akka-persistence-experimental" % akkaV)
}
