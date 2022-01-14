buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    kotlin("js") version "1.6.10"
}

group = "gmp-wasm-kotlin"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC3")

    // Fast, WASM port of GMP for big integers
    implementation(npm("gmp-wasm", "0.9.4"))

    // Generic stuff
    implementation(kotlin("stdlib", "1.6.10"))

    // Miscellaneous utility functions
    implementation("io.ktor:ktor-utils:1.6.7")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.290-kotlin-1.6.10")

    // Unit testing support
    testImplementation(kotlin("test-js", "1.6.10"))
    testImplementation(kotlin("test-annotations-common", "1.6.10"))

    // runTest() for running suspend functions in tests
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0-RC3")

    // Fancy property-based testing
    testImplementation("io.kotest:kotest-property:5.0.1")
}

kotlin {
    js(LEGACY) {
        moduleName = "electionguard"

        useCommonJs()
        binaries.executable()

        nodejs {
            version = "16.13.1"

            testTask {
                useMocha {
                    // thirty seconds rather than the default of two seconds
                    timeout = "30000"
                }

                testLogging {
                    showExceptions = true
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
    }
}