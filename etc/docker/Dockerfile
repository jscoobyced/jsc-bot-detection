FROM gradle:8-alpine AS build

WORKDIR /home/gradle/src

COPY src src
COPY build.gradle.kts gradle.properties gradlew settings.gradle.kts ./

RUN ls -al
RUN gradle build

FROM openjdk:17-alpine

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/jsc-bot-detection-all.jar"]