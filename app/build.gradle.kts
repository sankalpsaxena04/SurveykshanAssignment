plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.surveykshanassignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.surveykshanassignment"
        minSdk = 24
        targetSdk = 35
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
        buildConfig= true
        viewBinding=true
        dataBinding= true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //viewpager
    implementation ("androidx.viewpager2:viewpager2:1.1.0")

    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    //Gson
    implementation ("com.google.code.gson:gson:2.11.0")

    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-android-compiler:2.49")
    implementation ("androidx.hilt:hilt-common:1.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    //Glide
    val glide_version = "4.13.2"
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    implementation ("com.github.bumptech.glide:okhttp3-integration:$glide_version")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
}
kapt {
    correctErrorTypes= true
}