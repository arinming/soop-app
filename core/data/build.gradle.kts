plugins {
    alias(libs.plugins.soop.android.library)
    alias(libs.plugins.soop.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.soop.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
}