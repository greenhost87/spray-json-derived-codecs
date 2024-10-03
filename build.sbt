lazy val root = (project in file(".")).settings(
  organization                 := "com.github.greenhost87",
  scalaVersion                 := "3.5.0",
  version                      := "0.0.1",
  libraryDependencies ++= Seq(
    "io.spray"      %% "spray-json" % "1.3.6"  % "compile",
    "org.scalatest" %% "scalatest"  % "3.2.18" % "test"
  ),
  name                         := "spray-json-derived-codecs",
  Compile / mappings           := Seq(),
  packageDoc / mappings        := Seq(),
  packageDoc / publishArtifact := false,
  githubOwner                  := "greenhost87",
  githubRepository             := "spray-json-derived-codecs",
  scalacOptions                := Seq(
    "-deprecation",
    "-feature",
    "-Xmax-inlines:64",
    "-Wunused:imports"
  )
)
