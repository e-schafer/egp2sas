import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

name := "egp2sas"
organization := "org.vollgaz.sas"
scalaVersion := "2.12.8"

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
    "com.github.scopt" %% "scopt" % "4.0.0-RC2",
    "org.scalatest" %% "scalatest" % "3.1.0-RC1" % Test
)


//releasePublishArtifactsAction := assembly.value
releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    pushChanges
)

// sbt-assembly
mainClass in(Compile, run) := Some("org.vollgaz.sas.Main")
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true)


credentials += Credentials(Path.userHome / ".sbt" / "credentials")

artifact in(Compile, assembly) := {
    val art = (artifact in(Compile, assembly)).value
    art.withClassifier(Some("assembly"))
}

addArtifact(artifact in(Compile, assembly), assembly)