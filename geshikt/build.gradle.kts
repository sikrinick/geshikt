import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("maven-publish")
}

group = "io.github.sikrinick"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.api-client:google-api-client:2.0.1")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20220927-2.0.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20220927-2.0.0")
    implementation("com.google.apis:google-api-services-drive:v3-rev20221023-2.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}

configure<PublishingExtension> {
    val usernameKey = "SONATYPE_USERNAME"
    val passwordKey = "SONATYPE_PASSWORD"

    repositories {
        maven {
            credentials {
                username = findProperty(usernameKey)?.toString() ?: System.getenv(usernameKey) ?: ""
                password = findProperty(passwordKey)?.toString() ?: System.getenv(passwordKey) ?: ""
            }
            val root = "https://s01.oss.sonatype.org"
            url = uri(if (version.toString().contains("-SNAPSHOT")) {
                "$root/content/repositories/snapshots"
            } else {
                "$root/service/local/staging/deploy/maven2/"
            })
        }
    }
}