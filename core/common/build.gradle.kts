plugins {
    alias(libs.plugins.rmworld.android.library.compose)
}

android {
    namespace = "com.rmworld.core.common"
}
dependencies{
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.annotation.jvm)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
}