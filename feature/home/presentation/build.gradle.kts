plugins {
    alias(libs.plugins.rmworld.android.library.compose)
    alias(libs.plugins.rmworld.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.home.presentation"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.navigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.serialization.json)

    // Projects
    implementation(projects.feature.home.domain)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)


    debugImplementation(libs.androidx.ui)
}