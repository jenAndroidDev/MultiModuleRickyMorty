plugins {
    alias(libs.plugins.rmworld.android.library)
    alias(libs.plugins.ksp)
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
    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.kotlinx.serialization.json)

    // Project modules
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.feature.home.domain)
    implementation(projects.core.testing)

    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
}