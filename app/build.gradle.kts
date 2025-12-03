import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.test_lab_week_12"
    compileSdk = 36

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if(localPropertiesFile.exists() && localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    val tmdbApiKey = localProperties.getProperty("TMDB_API_KEY") ?: error("TMDB API Key not found in local.properties file")

    buildFeatures {
        viewBinding = true
        buildConfig = true
        resValues = true
    }

    defaultConfig {
        applicationId = "com.example.test_lab_week_12"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "TMDB_API_KEY", "$tmdbApiKey")
        }
        debug {
            buildConfigField("String", "TMDB_API_KEY", "$tmdbApiKey")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.recyclerview)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.moshi.kotlin)
    implementation("com.squareup.moshi:moshi")
    implementation("com.squareup.moshi:moshi-kotlin")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
