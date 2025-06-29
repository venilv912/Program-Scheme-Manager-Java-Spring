@echo off
setlocal
set MAVEN_WRAPPER_JAR=".mvn\wrapper\maven-wrapper.jar"
if not exist %MAVEN_WRAPPER_JAR% (
  echo Maven wrapper not found. Please install Maven manually.
  exit /b 1
)
java -jar %MAVEN_WRAPPER_JAR% %*
