import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeCompiler)
}

val apiKeyProperties = loadIntoProjectProperties(filePath = "apikey.properties")
val versionProperties = loadIntoProjectProperties(filePath = "version.properties")

android {
    namespace = "com.dimadef.flickr.app"

    defaultConfig {
        applicationId = "com.dimadef.flickr"
        versionCode = (versionProperties["appVersionCode"] as String?)?.toInt() ?: 1
        versionName = versionProperties["appVersionName"] as String? ?: "0.0.1"
        buildConfigField(
            "String",
            "FLICKR_API_KEY",
            apiKeyProperties["FLICKR_KEY"] as String? ?: ""
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources.excludes += setOf(
            "META-INF/LICENSE.md",
            "META-INF/LICENSE-notice.md",
            "META-INF/**previous-compilation-data.bin",
            "DebugProbesKt.bin",
            "META-INF/proguard/*",
            "/*.properties",
            "META-INF/*.properties"
        )
    }
}

dependencies {
    implementation(projects.library.ui)
    implementation(projects.data.network)
    implementation(projects.data.photosList)
    implementation(projects.domain.model)
    implementation(projects.feature.photosList)
    implementation(projects.feature.photoDetail)
    implementation(libs.appcompat)
    implementation(platform(libs.composeBom))
    implementation(libs.hiltNavigationCompose)
    implementation(libs.composeActivity)
    implementation(libs.composeMaterial3)
    implementation(libs.accompanistInsets)
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
}

// Load keys into project properties
fun loadIntoProjectProperties(filePath: String): Properties {
    val propertiesFile: File = rootProject.file(filePath)
    val properties = Properties()
    if (propertiesFile.exists()) {
        propertiesFile.inputStream().use(properties::load)
    }
    return properties
}
