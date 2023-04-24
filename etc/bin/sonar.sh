#!/bin/bash

sed -i 's/SONAR_PROJECT_KEY/IndyTheDog_jsc-bot-detection/' build.gradle.kts
sed -i 's/SONAR_ORGANISATION/indythedog/' build.gradle.kts
sed -i 's/SONAR_HOST/https:\/\/sonarcloud.io/' build.gradle.kts
./gradlew build sonarqube --info