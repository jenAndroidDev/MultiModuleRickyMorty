plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rmworld.core.testing"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
dependencies {
    implementation(libs.kotlinx.coroutines.test)
    api(libs.junit)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}