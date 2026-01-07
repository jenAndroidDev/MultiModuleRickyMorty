plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rmworld.feature.detail.data"

    buildFeatures {
        compose = true
    }
}
dependencies{
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(projects.feature.detail.domain)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(libs.retrofit.core)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

}