import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("kotlin-kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.network"
    compileSdk = 34

    buildTypes {
        release {
            isMinifyEnabled = false
            android.buildFeatures.buildConfig = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                gradleLocalProperties(rootDir,providers).getProperty("base_url")
            )
        }
        debug {
            android.buildFeatures.buildConfig = true
            buildConfigField(
                "String",
                "BASE_URL",
                gradleLocalProperties(rootDir,providers).getProperty("base_url")
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {

    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.hilt.android)
    implementation(libs.retrofit.core)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit.kotlin.serialization)
    kapt(libs.hilt.compiler)

}
