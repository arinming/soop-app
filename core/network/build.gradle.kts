plugins {
    alias(libs.plugins.soop.android.library)
    alias(libs.plugins.soop.hilt)
    id("kotlinx-serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.soop.network"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.kotlinx.coroutines.test)
}