plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.detail.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.androidx.core.ktx)

    implementation(libs.javax.inject)
    // Required for @Serializable in domain models
    implementation(libs.kotlinx.serialization.json)
}