plugins {
    alias(libs.plugins.rmworld.android.library.compose)
}
android {
    namespace = "com.rmworld.core"
}
dependencies{
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
}