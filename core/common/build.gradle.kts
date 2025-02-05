plugins {
    alias(libs.plugins.soop.jvm.library)
    alias(libs.plugins.soop.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}