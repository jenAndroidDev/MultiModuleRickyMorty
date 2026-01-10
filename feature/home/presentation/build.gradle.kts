plugins {
    alias(libs.plugins.rmworld.android.library.compose)
    alias(libs.plugins.ksp)
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
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    implementation(libs.androidx.core.ktx)
    ksp(libs.hilt.android.compiler)

    implementation(libs.kotlinx.serialization.json)

    // Projects
    implementation(projects.feature.home.domain)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    
    // Coil for image loading
    implementation(libs.coil.compose)

    // Android Studio Preview support
    /*implementation(libs.androidx.ui.tooling.preview)*/
    debugImplementation(libs.androidx.ui)
}