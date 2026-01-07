plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.ksp)
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
    implementation(libs.gson)
    implementation(libs.timber)
    implementation(libs.androidx.annotation.jvm)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.converter.gson)

}