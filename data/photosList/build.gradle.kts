plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dimadef.flickr.data.photosList"
}

dependencies {
    implementation(projects.data.common)
    implementation(projects.domain.photosList)
    implementation(projects.domain.model)
    implementation(projects.library.coroutine)
    implementation(libs.coroutinesCore)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    testImplementation(libs.bundles.testCore)
}
