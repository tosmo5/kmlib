import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    kotlin("multiplatform") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
    id("maven-publish")
    id("signing")
}

group = "io.github.tosmo5"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.22")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
    }
}

// ----------------------------
// public configuration
// ----------------------------
val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

val secretPropsFile = project.rootProject.file("publish/local.properties")
secretPropsFile.reader().use {
    Properties().apply { load(it) }
        .onEach { (name, value) -> ext[name.toString()] = value }
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        maven {
            name = "kmlib"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }

    publications.withType<MavenPublication> {
        artifact(javadocJar.get())

        pom {
            name.set("kotlin multiplatform lib")
            description.set("kotlin多平台库")
            url.set("https://github.com/tosmo5/kmlib")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    name.set("Thomas Miao")
                    email.set("tosmo5@163.com")
                }
            }

            scm {
                url.set("https://github.com/tosmo5/kmlib")

            }
        }
    }
}

signing {
    sign(publishing.publications)
}