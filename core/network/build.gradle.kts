plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.rmworld.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.core.network"
}

dependencies{
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.javax.inject)
    implementation(libs.logging.interceptor)
    implementation(libs.timber)
    implementation(libs.androidx.annotation.jvm)
}