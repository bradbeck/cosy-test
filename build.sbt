organization := "com.feedzai"

name := "cosy-test"

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12", "2.12.4")

scalacOptions ++= Seq(
  "-feature", "-deprecation",
  "-Xlint", "-Ywarn-unused-import", "-Xfatal-warnings"
)

scalacOptions in (Compile, doc) ++= {
  if (scalaBinaryVersion.value == "2.12") Seq("-no-java-comments") else Seq.empty
}

libraryDependencies ++= Seq(
  "org.slf4j"         % "slf4j-api"              % "1.7.25",
  "org.scalatest"     %% "scalatest"             % "3.0.4"   % Provided,
  "io.gatling"        % "gatling-test-framework" % "2.2.5"   % Provided,
  "com.novocode"      % "junit-interface"        % "0.11"    % Provided,
  "org.hamcrest"      % "hamcrest-junit"         % "2.0.0.0" % Provided,
  "org.testng"        % "testng"                 % "6.14.2"  % Provided,
  "org.junit.jupiter" % "junit-jupiter-api"      % "5.1.0"   % Provided
)

/* Resolver needed for sbt-jupiter-interface */
resolvers in ThisBuild += Resolver.jcenterRepo

/* TestNG Settings */

enablePlugins(TestNGPlugin)
testNGVersion := "6.14.2"
testNGSuites  := Seq("src/test/scala/com/feedzai/cosytest/testng/suite.xml")

/* Extra metadata for releases */

homepage := Some(url("https://github.com/feedzai/cosy-test"))

scmInfo := Some(ScmInfo(
  url("https://github.com/feedzai/cosy-test"),
  "scm:git@github.com:feedzai/cosy-test.git"
))

developers := List(
  Developer(id="Timunas", name="João Suzana", email="joao.suzana@feedzai.com", url=url("https://github.com/Timunas")),
  Developer(id="stanch", name="Nick Stanchenko", email="nick.stanch@feedzai.com", url=url("https://github.com/stanch"))
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

/* Publishing */

useGpg := false
usePgpKeyHex("AEF53A05F9453BD9")
pgpPublicRing := baseDirectory.value / ".gnupg" / "pubring.gpg"
pgpSecretRing := baseDirectory.value / ".gnupg" / "secring.gpg"
pgpPassphrase := sys.env.get("PGP_PASS").map(_.toArray)

sonatypeProfileName := organization.value

credentials += Credentials(
  "Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  sys.env.getOrElse("SONATYPE_USER", ""),
  sys.env.getOrElse("SONATYPE_PASS", "")
)

publishTo := Some(Opts.resolver.sonatypeStaging)
