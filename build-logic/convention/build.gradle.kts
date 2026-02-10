
plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)

}
group = "com.rmworld.app.buildlogic"

java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradleApiPlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    lintChecks(libs.androidx.lint.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplication"){
            id = libs.plugins.rmworld.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary"){
            id = libs.plugins.rmworld.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidComposeLibrary"){
            id = libs.plugins.rmworld.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("hilt"){
            id = libs.plugins.rmworld.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("androidLint") {
            id = libs.plugins.rmworld.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }

    }
}