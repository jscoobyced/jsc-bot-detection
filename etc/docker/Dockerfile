FROM gradle:8-alpine AS build

RUN apk add openjdk11
WORKDIR /home/gradle/src

COPY src src
COPY build.gradle.kts gradle.properties gradlew settings.gradle.kts ./

RUN gradle --no-daemon buildFatJar

FROM openjdk:17-alpine

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/jsc-bot-detection-all.jar /app/

EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/jsc-bot-detection-all.jar"]