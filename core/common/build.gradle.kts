plugins {
    alias(libs.plugins.rmworld.android.library)
}

android {
    namespace = "com.rmworld.core.common"
}
dependencies{
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.annotation.jvm)
}