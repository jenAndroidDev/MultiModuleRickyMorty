plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rmworld.core.network"
    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}
dependencies{
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.core)
    implementation(libs.javax.inject)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.timber)
    implementation(libs.androidx.annotation.jvm)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)


}