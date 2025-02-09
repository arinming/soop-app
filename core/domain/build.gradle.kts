plugins {
    alias(libs.plugins.soop.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.soop.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax.inject)
    implementation(libs.paging.common)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    testImplementation(projects.core.testing)
}