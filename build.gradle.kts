plugins {
    kotlin("jvm") version "2.0.20" apply false
    kotlin("plugin.spring") version "2.0.20" apply false
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
//        maven { url = uri("https://repo.spring.io/release") }
    }
}


// ✅ Общие настройки для всех подпроектов
subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    group = "dev.folomkin"
    version = "1.0.0"

    // доступ к extension dependencyManagement
    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }

    val appModules = listOf(
        "auth-service",
        "order-service",
        "user-service"
    )

    val feignModules = listOf(
        "user-service",
        "order-service"
    )

    plugins.withId("org.jetbrains.kotlin.jvm") {
        if (name in feignModules) {
            dependencies {
                add(
                    "implementation",
                    "org.springframework.cloud:spring-cloud-starter-openfeign"
                )
            }
        }
        if (name in appModules) {
            dependencies {
                add("implementation", project(":shared"))
                add("implementation", kotlin("stdlib"))
                add(
                    "implementation",
                    "org.springframework.boot:spring-boot-starter-web"
                )
//                add(
//                    "implementation",
//                    "org.springframework.cloud:spring-cloud-starter-openfeign"
//                )
                add(
                    "implementation",
                    "org.jetbrains.kotlin:kotlin-stdlib"
                )
                add(
                    "implementation",
                    "org.jetbrains.kotlin:kotlin-reflect"
                )
                add(
                    "implementation",
                    "org.springframework.kafka:spring-kafka"
                )
                add(
                    "testImplementation",
                    "org.springframework.boot:spring-boot-starter-test"
                )
                add(
                    "testImplementation",
                    "org.jetbrains.kotlin:kotlin-test-junit5"
                )
                add(
                    "testImplementation",
                    "org.junit.platform:junit-platform-launcher"
                )
                add(
                    "testImplementation",
                    "org.springframework.kafka:spring-kafka-test"
                )

                add(
                    "implementation",
                    "com.fasterxml.jackson.module:jackson-module-kotlin"
                )
            }
        }
    }

}

