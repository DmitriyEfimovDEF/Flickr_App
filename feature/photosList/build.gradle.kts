plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.dimadef.flickr.feature.photosList"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.library.coroutine)
    implementation(projects.library.arch)
    implementation(projects.library.ui)
    implementation(projects.domain.model)
    implementation(projects.domain.photosList)
    implementation(platform(libs.composeBom))
    implementation(libs.lifecycleCompose)
    implementation(libs.composeNavigation)
    implementation(libs.immutableCollections)
    implementation(libs.composeMaterial3)
    implementation(libs.composeFlowLayout)
    implementation(libs.composeTooling)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

}
