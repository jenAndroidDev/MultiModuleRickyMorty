plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.feature.detail.presentation"
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
    buildTypes{
        debug { isMinifyEnabled = false }
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
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.android.compiler)
    implementation(libs.kotlinx.serialization.json)

    //core
    implementation(projects.feature.home.domain)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    //coil for image loading
    implementation(libs.coil.compose)


    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

}