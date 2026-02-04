plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.rmworld.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.detail.data"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    // Domain and Core
    implementation(projects.feature.detail.domain)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.testing)

    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.logging.interceptor)

    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
