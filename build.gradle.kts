// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set( "kotlin_version", "1.8.20")
    }

    dependencies {
        val kotlin_version: String by rootProject.extra
        // Add the dependency for the Google services Gradle plugin
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

    }

}


plugins {
    id ("com.android.application") version "8.0.0" apply false
    id ("com.android.library") version "8.0.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}