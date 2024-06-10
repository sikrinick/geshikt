import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "1.9.21"
    application
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

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.set(freeCompilerArgs.get() + "-Xcontext-receivers")
    }
}
