import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven (url = "https://jitpack.io")
        mavenLocal()
    }
}



plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
}

val mapboxTokenProp = providers.gradleProperty("access_token")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven(url ="https://jitpack.io")

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        }
    }
}



rootProject.name = "MapboxAutoCompleteTextField"
include(":app")
include(":compose")
