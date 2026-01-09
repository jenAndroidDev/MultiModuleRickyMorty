plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.home.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.androidx.core.ktx)
    
    // Required for @Serializable in domain models
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.serialization.json)
}