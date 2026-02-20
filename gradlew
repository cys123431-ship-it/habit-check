#!/usr/bin/env sh
DEFAULT_JVM_OPTS=""
APP_HOME=`dirname "$0"`
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
exec java $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"