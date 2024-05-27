name         := "slickless"
organization := "net.scalax.slickless"
version      := "0.3.8-M1"
scalaVersion := scalaV.v213

crossScalaVersions := Seq(scalaV.v212, scalaV.v213, scalaV.v3)

licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0"))

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Xfatal-warnings"
)

libraryDependencies ++= libScalax.`slick`.value
libraryDependencies ++= libScalax.`shapeless`.value
libraryDependencies ++= libScalax.`h2`.value.map(_ % Test)
libraryDependencies ++= libScalax.`scalatest`.value.map(_ % Test)
libraryDependencies ++= libScalax.`logback-classic`.value.map(_ % Test)

scalafmtOnCompile := true

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / pomExtra := {
  <url>https://github.com/scalax/slickless</url>
  <scm>
    <connection>scm:git:github.com/scalax/slickless</connection>
    <developerConnection>scm:git:git@github.com:scalax/slickless</developerConnection>
    <url>github.com/scalax/slickless</url>
  </scm>
  <developers>
    <developer>
      <id>d6y</id>
      <name>Richard Dallaway</name>
      <url>http://twitter.com/d6y</url>
    </developer>
    <developer>
      <id>davegurnell</id>
      <name>Dave Gurnell</name>
      <url>http://twitter.com/davegurnell</url>
    </developer>
    <developer>
      <id>milessabin</id>
      <name>Miles Sabin</name>
      <url>http://twitter.com/milessabin</url>
    </developer>
    <developer>
      <id>djx314</id>
      <name>djx314</name>
      <url>https://github.com/djx314</url>
    </developer>
  </developers>
}

ThisBuild / versionScheme := Some("early-semver")

ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
