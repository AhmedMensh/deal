import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.boarding"
    compileSdk = 32


    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha01"
    }


}

dependencies {

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(libs.bundles.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.compose.navigation)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}