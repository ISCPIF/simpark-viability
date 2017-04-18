
name := "simpark-viability"

scalaVersion := "2.12.1"

def viabilitreeVersion = "2.0-alpha4"
//def viabilitreeVersion = "2.0-SNAPSHOT"

resolvers += Resolver.sonatypeRepo("public")
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "fr.iscpif.viabilitree" %% "export" % viabilitreeVersion
libraryDependencies += "fr.iscpif.viabilitree" %% "viability" % viabilitreeVersion

