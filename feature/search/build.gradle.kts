plugins {
    alias(libs.plugins.soop.android.feature)
    alias(libs.plugins.soop.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.soop.search"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
}