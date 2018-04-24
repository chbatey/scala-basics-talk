scalaVersion := "2.12.5"

name := "scala-beyond-basics"

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % "test",
  "io.gatling"            % "gatling-test-framework"    % "2.3.0" % "test",
)

enablePlugins(GatlingPlugin)
enablePlugins(ParadoxRevealPlugin)

paradoxGroups := Map("Language" -> Seq("Scala", "Java"))
paradoxProperties += ("selectedLanguage" → sys.env.getOrElse("PARADOX_LANGUAGE", "Scala"))

paradox := (Compile / paradox dependsOn Compile / compile).value