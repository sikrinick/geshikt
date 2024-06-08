import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("maven-publish")
}

group = "io.github.sikrinick"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.sikrinick:geshikt")
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

    publications {
        withType<MavenPublication> {
            pom {
                name.set("${project.group}:${project.description}")
                description.set(name)
                url.set("https://github.com/sikrinick/sally")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("sikrinick")
                        name.set("Mykyta Sikriier")
                        email.set("sikrinick@gmail.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            credentials {
                username = findProperty(usernameKey)?.toString() ?: System.getenv(usernameKey) ?: ""
                password = findProperty(passwordKey)?.toString() ?: System.getenv(passwordKey) ?: ""
            }
            val root = "https://s01.oss.sonatype.org"
            url = uri(if (version.toString().endsWith("-SNAPSHOT")) {
                "$root/content/repositories/snapshots"
            } else {
                "$root/service/local/staging/deploy/maven2/"
            })
            print("Publishing version $version to $url")
        }
    }
}