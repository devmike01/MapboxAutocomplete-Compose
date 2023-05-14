import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.android.build.gradle.internal.scope.publishArtifactToDefaultVariant
import com.android.build.gradle.internal.utils.createPublishingInfoForLibrary
import org.jetbrains.kotlin.asJava.classes.lazyPub

plugins {
    id ("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("maven-publish")
}


group = "io.devmike.compose"
version = "1.0.2-alpha"

android {
    namespace = "io.devmike.compose"
    compileSdk = 33

    val mapboxToken: String = gradleLocalProperties(rootDir).getProperty("access_token")
    val mapboxUrl: String = gradleLocalProperties(rootDir).getProperty("mapbox_base_url")


    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        val baseMapboxUrl = "\"$mapboxUrl\""
        val mapboxAccessToken = "\"$mapboxToken\""
        release {
            buildConfigField("String", "MAPBOX_TOKEN", mapboxAccessToken)
            buildConfigField("String", "MAPBOX_URL", baseMapboxUrl)
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            buildConfigField("String", "MAPBOX_TOKEN", mapboxAccessToken)
            buildConfigField("String", "MAPBOX_URL", baseMapboxUrl)
        }

    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"

//        freeCompilerArgs += listOf(
//            "-P",
//            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=1.8.20"
//        )
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    kotlin{
        jvmToolchain(17)
    }


    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

afterEvaluate {
    publishing {
        publications.create<MavenPublication>("maven") {
            //from(components::class.java)
            groupId = "io.devmike.compose"
            artifactId = "mapboxautocomplete"
            version = "1.0.9-alpha"
            pom.packaging = "jar"
            artifact("$buildDir/libs/mapboxautocomplete.jar")
        }
    }
}
dependencies {
    val hiltVersion = 2.44

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("org.mockito:mockito-core:3.+")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.1.0")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation ("androidx.core:core-ktx:1.8.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
}

