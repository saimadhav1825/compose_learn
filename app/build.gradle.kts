plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.composelearn"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.composelearn"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-util")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.navigation:navigation-compose:2.8.0-beta04")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Material Design
    implementation("com.google.android.material:material:1.10.0")

    //hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    //Exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    // ktor
    implementation("io.ktor:ktor-client-android:2.3.3")
    implementation("io.ktor:ktor-client-serialization:1.5.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
    implementation("io.ktor:ktor-client-logging-jvm:2.3.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-client-core:2.3.5")

    //firebase notification
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-messaging-ktx:")
    implementation("com.google.firebase:firebase-analytics")

    //Splash Screen Api
    implementation("androidx.core:core-splashscreen:1.0.0-beta02")
    implementation("androidx.profileinstaller:profileinstaller:1.3.0-alpha02")
    implementation("com.google.code.gson:gson:2.9.1")


    //paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("io.github.theapache64:rebugger:1.0.0-rc03")
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
// Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}