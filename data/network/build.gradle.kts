plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.data.common)
    implementation(projects.domain.model)
    implementation(libs.retrofit)
    implementation(platform(libs.okhttpBom))
    implementation(libs.okhttp)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.retrofitGsonConverter)
    implementation(libs.okhttp)
    implementation(libs.hiltCore)
    implementation(libs.annotationJvm)
    ksp(libs.hiltCompiler)
}
