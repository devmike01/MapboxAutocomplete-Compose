pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven (url = "https://jitpack.io")
        maven ( url ="https://oss.sonatype.org/content/repositories/snapshots" )
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
        maven(url ="https://jitpack.io")
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        }
    }
}



//id("com.github.dcendents.android-maven")
rootProject.name = "MapboxAutoCompleteTextField"
include(":app")
include(":compose")
