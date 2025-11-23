plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.jetbrainsKotlinParcelize)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.secretPlugin)
    alias(libs.plugins.screenshot)
}

secrets {
    defaultPropertiesFileName = "secret.defaults.properties"
}

android {
    namespace = "com.stefanoq21.whatsappgemini"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.stefanoq21.whatsappgemini"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }

    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.browser)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //gemini
    implementation(libs.generativeai)

    //navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)
    implementation(libs.androidx.adaptive.navigation.android)

    // permissions
    //implementation "com.google.accompanist:accompanist-permissions:0.32.0"

    //collectAsStateWithLifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)

    // pagination
    //implementation(libs.androidx.paging.compose)

    //splash
    implementation(libs.androidx.core.splashscreen)


    //coil images
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.gif)

    //palette
    implementation(libs.androidx.palette.ktx)

    //koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)


    //Ktor HttpClient
    /* implementation(libs.ktor.client.core)
     implementation(libs.ktor.client.android)
     implementation(libs.ktor.client.content.negotiation)
     implementation(libs.ktor.serialization.kotlinx.json)
     implementation(libs.ktor.client.logging)
     debugImplementation(libs.slf4j.simple)*/

    //Json serialization kotlin
    implementation(libs.kotlinx.serialization.json)

    //preference
    implementation(libs.androidx.datastore.preferences)


    // Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)


    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)


    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.agent)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
    androidTestImplementation(libs.androidx.room.testing)

    screenshotTestImplementation(libs.screenshot.validation.api)
    screenshotTestImplementation(libs.androidx.ui.tooling)
}