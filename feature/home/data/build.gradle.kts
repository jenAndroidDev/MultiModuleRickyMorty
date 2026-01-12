plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.rmworld.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rmworld.feature.home.data"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("Boolean", "LOG_HTTP_CALLS", "true")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("Boolean", "LOG_HTTP_CALLS", "false")
        }
    }

}
dependencies{
    implementation(libs.kotlinx.serialization.json)

    // Project modules
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.feature.home.domain)
    implementation(projects.core.testing)

    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.logging.interceptor)
}
