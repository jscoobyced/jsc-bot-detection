JSC Bot Detection Service
--

[![codecov](https://codecov.io/gh/jscoobyced/jsc-bot-detection/branch/main/graph/badge.svg?token=PH2V3Y06AF)](https://codecov.io/gh/jscoobyced/jsc-bot-detection)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=bugs)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)

This project aims at providing a base bot detection and classification in order to help protect your environments.

# Getting started - Development mode

You will need to download the 51Degrees [51Degrees-LiteV4.1.hash](https://github.com/51Degrees/device-detection-data/raw/master/51Degrees-LiteV4.1.hash) file and place it to your `resources` folder.

## GitHub Actions

In order to pass all the steps in GitHub actions, you need to:
- Create an account in https://codecov.io (login using GH so it creates an organization with your repo name)
  - Create a token and save it in GH project settings under `Secrets`
    - Name: CODECOV_TOKEN
    - Value: the token from codecov
- Create an account in https://sonarcloud.io (login using GH so it creates an organization with your repo name)
  - Create a token and save it in GH project settings under `Secrets`
    - Name: SONARCLOUD_TOKEN
    - Value: the token from sonarcloud

You will need to setup your `Quality Gate` and `New Code` in sonarcloud for best reporting. Remove the `Coverage` from the `Quality Gate` as we are using Codecov.
If you have custom organization and project name, you can override them in the [etc/bin/sonar.sh](etc/bin/sonar.sh) file.

## Running the application

By default, whichever way you are going to execute the application, it will run in development mode. For production mode, see the below section. 

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

# Production mode
To run in production, build the image and provide a `.env` file or the fields in it as environment variable to your runtime.

Soon will be added deployment facilities.