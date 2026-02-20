#!/usr/bin/env sh
DEFAULT_JVM_OPTS=""
APP_HOME=`dirname "$0"`
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

if [ -f "$WRAPPER_JAR" ]; then
  exec java $DEFAULT_JVM_OPTS -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
else
  exec gradle "$@"
fi
