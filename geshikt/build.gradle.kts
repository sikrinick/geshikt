import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.0"
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.9.20"
    signing
}

group = "io.github.sikrinick"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.api-client:google-api-client:2.6.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.36.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20240514-2.0.0")
    implementation("com.google.apis:google-api-services-drive:v3-rev20240521-2.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.set(freeCompilerArgs.get() + "-Xcontext-receivers")
    }
}

val dokkaHtml by tasks.getting(DokkaTask::class)
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar)
            artifact(javadocJar)

            pom {
                name.set("${project.group}:${project.description}")
                description.set(name)
                url.set("https://github.com/sikrinick/geshikt")
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
                scm {
                    url.set("scm:git:https://github.com/sikrinick/geshikt.git")
                    connection.set("scm:git:https://github.com/sikrinick/geshikt.git")
                    developerConnection.set("scm:git:https://github.com/sikrinick/geshikt.git")
                }
            }
            the<SigningExtension>().sign(this)
        }
    }

    repositories {
        maven {
            val usernameKey = "SONATYPE_USERNAME"
            val passwordKey = "SONATYPE_PASSWORD"

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
