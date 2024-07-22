plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.openclassrooms.tajmahal"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.openclassrooms.tajmahal"
        minSdk = 24
        targetSdk = 33
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.work:work-runtime:2.8.1")
    implementation("com.google.firebase:firebase-firestore:25.0.0")
    val hiltVersion = "2.44"

    //Hilt
    implementation("com.google.dagger:hilt-android:${hiltVersion}")
    annotationProcessor("com.google.dagger:hilt-compiler:${hiltVersion}")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.work:work-runtime:2.8.1")
    implementation("androidx.core:core-splashscreen:1.0.0")

    // bibliotheque glide pour url image
    implementation ("com.github.bumptech.glide:glide:4.10.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
