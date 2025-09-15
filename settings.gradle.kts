plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlin-microservice-master"
include("auth-service")
include("order-service")
include("shared")
include("user-service")