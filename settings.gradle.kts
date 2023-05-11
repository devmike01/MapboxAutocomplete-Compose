import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}




val mapboxTokenProp = providers.gradleProperty("access_token")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        }
    }
}

rootProject.name = "MapboxAutoCompleteTextField"
include(":app")
include(":mapboxautocomplete")
