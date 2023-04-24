#!/bin/bash

SONAR_PROJECT_KEY=${GITHUB_REPOSITORY_OWNER}
SONAR_ORGANISATION=${GITHUB_REPOSITORY_OWNER,,}

sed -i "s/SONAR_PROJECT_KEY/${SONAR_PROJECT_KEY}_jsc-bot-detection/" build.gradle.kts
sed -i "s/SONAR_ORGANISATION/${SONAR_ORGANISATION}/" build.gradle.kts
sed -i "s/SONAR_HOST/https:\/\/sonarcloud.io/" build.gradle.kts

cat build.gradle.kts

# ./gradlew build sonarqube --info