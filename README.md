JSC Bot Detection Service
--

[![codecov](https://codecov.io/gh/jscoobyced/jsc-bot-detection/branch/main/graph/badge.svg?token=PH2V3Y06AF)](https://codecov.io/gh/jscoobyced/jsc-bot-detection)

This project aims at providing a base bot detection and classification in order to help protect your environments.

# Getting started - Development mode

By default, whichever way you are going to execute the application, it will run in development mode. For production mode, see the below section. 

## Run in IntelliJ
This project uses Kotlin, and was created in IntelliJ Community edition. You should be able to clone this repository then open the project in IntelliJ.

Browse in the project to find the `Application.kt` class, right click and "Run". The service will start on http://0.0.0.0:8010

Alternatively, you can do a `./gradlew run` from the root of th project (or via IntelliJ interface).

## Docker-compose

You can also run via docker-compose.

To do so, copy the `.env.example` file to `.env`. Then run `docker-compose up api`.

# Production mode
To run in production, build the image and provide a `.env` file or the fields in it as environment variable to your runtime.

Soon will be added deployment facilities.