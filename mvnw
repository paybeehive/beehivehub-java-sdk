#!/bin/sh
# Maven Wrapper Script
# Licensed to the Apache Software Foundation (ASF)

set -e

MAVEN_WRAPPER_PROPERTIES_PATH=".mvn/wrapper/maven-wrapper.properties"
MAVEN_WRAPPER_JAR_PATH=".mvn/wrapper/maven-wrapper.jar"

if [ -f ".mvn/wrapper/maven-wrapper.jar" ]; then
    exec "$JAVA_HOME/bin/java" -classpath "$MAVEN_WRAPPER_JAR_PATH" \
        org.apache.maven.wrapper.MavenWrapperMain "$@"
fi

DOWNLOAD_URL=$(grep "wrapperUrl" "$MAVEN_WRAPPER_PROPERTIES_PATH" | cut -d= -f2)
DISTRIBUTION_URL=$(grep "distributionUrl" "$MAVEN_WRAPPER_PROPERTIES_PATH" | cut -d= -f2)

MAVEN_USER_HOME="${MAVEN_USER_HOME:-$HOME/.m2}"
MAVEN_BASE_URL=$(echo "$DISTRIBUTION_URL" | sed 's|/[^/]*$||')
MAVEN_FILENAME=$(echo "$DISTRIBUTION_URL" | sed 's|.*/||')
MAVEN_FOLDER="${MAVEN_FILENAME%.zip}"
MAVEN_FOLDER="${MAVEN_FOLDER%-bin}"

MAVEN_HOME="$MAVEN_USER_HOME/wrapper/dists/$MAVEN_FOLDER"

if [ ! -d "$MAVEN_HOME/bin" ]; then
    mkdir -p "$MAVEN_HOME"
    echo "Downloading Maven from $DISTRIBUTION_URL ..."
    curl -L -o "$MAVEN_HOME/$MAVEN_FILENAME" "$DISTRIBUTION_URL"
    unzip -q "$MAVEN_HOME/$MAVEN_FILENAME" -d "$MAVEN_HOME"
    mv "$MAVEN_HOME"/*/* "$MAVEN_HOME/" 2>/dev/null || true
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
