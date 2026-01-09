plugins {
    alias(libs.plugins.rmworld.android.library.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.detail.presentation"
    compileSdk = 36
    defaultConfig {
        minSdk=26
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes{
        debug { isMinifyEnabled = false }
    }
}
dependencies{
    /*implementation(platform(libs.androidx.compose.bom))*/
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.android.compiler)
    implementation(libs.kotlinx.serialization.json)

    //core
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.feature.detail.domain)
    
    //coil for image loading
    implementation(libs.coil.compose)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

}