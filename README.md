JSC Bot Detection Service
--

[![codecov](https://codecov.io/gh/jscoobyced/jsc-bot-detection/branch/main/graph/badge.svg?token=PH2V3Y06AF)](https://codecov.io/gh/jscoobyced/jsc-bot-detection)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=bugs)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jscoobyced_jsc-bot-detection&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jscoobyced_jsc-bot-detection)

This project aims at providing a base bot detection and classification in order to help protect your environments.

# Getting started - Development mode

You will need to download the 51Degrees [51Degrees-LiteV4.1.hash](https://github.com/51Degrees/device-detection-data/raw/master/51Degrees-LiteV4.1.hash) file
and place it to your main sources `resources` folder.

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

Because of the other containers dependencies, we need to run using `docker-compose` command. There is a `Makefile` that can help simplify the commands to run.

### Setup

Before being able to run the application, we need a dependency from 51 Degrees that provide device type detection. It identifies if a request is from a mobile or desktop.
This setup step also build the dependencies.
The very first time you checkout this repo, run this command:
```
make setup
```

### Runtime

Once setup is complete, you can run the application with
```
make services
```

### Debugging

Coming soon!

# Production mode
To run in production, build the image and provide a `.env` file or the fields in it as environment variable to your runtime.

Soon will be added deployment facilities.