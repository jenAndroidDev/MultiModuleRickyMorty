plugins {
    alias(libs.plugins.rmworld.android.library.compose)
}
android {
    compileSdk = 36
    defaultConfig {
        minSdk = 26
    }
    namespace = "com.rmworld.core"
}
dependencies{

    //implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3) // Example

}