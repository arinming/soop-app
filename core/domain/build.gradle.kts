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

    implementation(libs.javax.inject)

    testImplementation(projects.core.testing)
}