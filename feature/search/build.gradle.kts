plugins {
    alias(libs.plugins.soop.android.feature)
    alias(libs.plugins.soop.android.library.compose)
}

android {
    namespace = "com.soop.search"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)

    implementation(libs.paging.common)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(projects.core.testing)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}