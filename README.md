JSC Bot Detection Service
--

[![codecov](https://codecov.io/gh/jscoobyced/jsc-bot-detection/branch/main/graph/badge.svg?token=PH2V3Y06AF)](https://codecov.io/gh/jscoobyced/jsc-bot-detection)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=bugs)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)


This project aims at providing a base bot detection and classification in order to help protect your environments.

# Getting started

## Development mode

By default, whichever way you are going to execute the application, it will run in development mode. For production mode, see the below section. 

You will need to download the free device binary file from [here](https://github.com/51Degrees/device-detection-data/raw/master/51Degrees-LiteV4.1.hash) and place it in the root of this project.

### Run in IntelliJ
This project uses Kotlin, and was created in IntelliJ Community edition. You should be able to clone this repository then open the project in IntelliJ.

Browse in the project to find the `Application.kt` class, right click and "Run". The service will start on http://0.0.0.0:8010

Alternatively, you can do a `./gradlew run` from the root of th project (or via IntelliJ interface).

### Docker-compose

You can also run via docker-compose.

Use IntelliJ `Services` panel to run the `api-dev` service. Alternatively run the command in a terminal:
```shell
docker-compose up -d api-dev
```

## Production mode
To run in production, build the image and provide a `.env` file or the fields in it as environment variable to your runtime.

Soon will be added deployment facilities.