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
    api(projects.core.common)
    api(projects.core.network)

    implementation(libs.paging.common)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(projects.core.testing)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}