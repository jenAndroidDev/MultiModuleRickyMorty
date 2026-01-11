plugins {
    alias(libs.plugins.rmworld.android.library.compose)
    alias(libs.plugins.rmworld.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.detail.presentation"
    buildFeatures {
        buildConfig = true
    }
    buildTypes{
        debug { isMinifyEnabled = false }
    }
}
dependencies{
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.navigation)

    implementation(libs.hilt.navigation)

    implementation(libs.kotlinx.serialization.json)

   /* implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)*/

    //core
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.feature.detail.domain)
    
    //coil for image loading
    implementation(libs.coil.compose)

    // Android Studio Preview support
    debugImplementation(libs.androidx.ui.tooling)

}