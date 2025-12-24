plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.rmworld.feature.home.data"
    compileSdk = 36

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("Boolean", "LOG_HTTP_CALLS", "true")
        }
        release {
            // Add the same fields for the 'release' build type
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("Boolean", "LOG_HTTP_CALLS", "false") // Typically false for release
            // You would also add ProGuard rules here for release builds
            // isMinifyEnabled = true
            // proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}
dependencies{
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material3)
    // or skip Material Design and build directly on top of foundational components
    implementation(libs.androidx.foundation)
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation(libs.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    //project
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.feature.home.domain)
    implementation(projects.core.testing)

    implementation(libs.retrofit.core)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

}