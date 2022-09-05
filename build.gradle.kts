import java.util.Properties

plugins {
    kotlin("multiplatform") version "1.7.10"
    `maven-publish`
}

group = "com.tomuvak.weak-reference"
version = "0.0.4-SNAPSHOT"

val localProperties = Properties()
project.rootProject.file("local.properties").takeIf { it.canRead() }?.inputStream()?.let(localProperties::load)
fun local(key: String): String? = localProperties.getProperty(key)

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/tomuvak/testing-gc-core")
        credentials {
            username = local("githubUser")
            password = local("githubToken")
        }
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            testTask {
                useMocha()
            }
        }
    }
    linuxX64()
    macosX64()
    mingwX64()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.tomuvak.testing-coroutines:testing-coroutines:0.0.3")
                implementation("com.tomuvak.testing-gc-core:testing-gc-core:0.0.6")
            }
        }

        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting

        val nativeMain by creating { dependsOn(commonMain) }
        val nativeTest by creating { dependsOn(commonTest) }

        val linuxX64Main by getting { dependsOn(nativeMain) }
        val linuxX64Test by getting { dependsOn(nativeTest) }
        val macosX64Main by getting { dependsOn(nativeMain) }
        val macosX64Test by getting { dependsOn(nativeTest) }
        val mingwX64Main by getting { dependsOn(nativeMain) }
        val mingwX64Test by getting { dependsOn(nativeTest) }
    }
}

local("githubRepository")?.let { githubRepository ->
    publishing {
        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/$githubRepository")
                credentials {
                    username = local("githubUser")
                    password = local("githubToken")
                }
            }
        }
    }
}
