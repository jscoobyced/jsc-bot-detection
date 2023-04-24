val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val sentryVersion: String by project
val kodeinVersion: String by project

plugins {
    kotlin("jvm") version "1.8.20"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
    id("org.jetbrains.kotlinx.kover") version "0.7.0-Alpha"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "io.narok"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.narok.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-compression-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.sentry:sentry:$sentryVersion")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
}

kover {
    disabledForProject = false
    useKoverTool()
}

koverReport {
    html {
        title = "JSC BOT Detection Service"
        onCheck = false

        filters {
            excludes {
                classes("io.narok.plugins.SentryKt")
            }
        }
    }
    verify {
        onCheck = true
        rule {
            filters {
                excludes {
                    classes("io.narok.plugins.SentryKt")
                }
            }
            bound {
                minValue = 88
                maxValue = 100
                metric = kotlinx.kover.gradle.plugin.dsl.MetricType.LINE
                aggregation = kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
            }
        }
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "SONAR_PROJECT_KEY")
        property("sonar.organization", "SONAR_ORGANISATION")
        property("sonar.host.url", "SONAR_HOST")
    }
}