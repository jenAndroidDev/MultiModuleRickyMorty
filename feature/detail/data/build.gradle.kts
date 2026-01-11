plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.rmworld.hilt)
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

    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
}