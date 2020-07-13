package dependencies

import org.gradle.api.JavaVersion

object Modules {
    object App {
        const val applicationId = "br.com.psyapp"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }
}

object Libraries {
    object Emotions {
        const val versionCode = 1
        const val versionName = "1.0.0"
    }
}

object Versions {
    const val minSdk = 21 // Android 5.0
    const val targetSdk = 29
    const val compileSdk = 29
    const val buildGradle = "4.0.0"
    const val kotlin = "1.3.72"
    const val buildTools = "30.0.0"
    val jdkSource = JavaVersion.VERSION_1_8
    val jdkTarget = JavaVersion.VERSION_1_8

    // Life Cycle
    const val lifeCycle = "2.2.0"
    // Fragment Navigation
    const val navigation = "2.3.0"
    // Google Services
    const val googleServices = "4.3.3"
    // Material Design
    const val materialDesign = "1.1.0"
    // Firebase
    const val firebaseAnalytics = "17.4.4"
    const val firebaseAuth = "19.3.2"
    const val firebaseCrashlyticsGradle = "2.2.0"
    const val firebaseCrashlytics = "17.1.1"
    // Koin
    const val koin = "2.1.5"
    // Retrofit
    const val retrofit = "2.9.0"
    // OkHttp
    const val okhttp = "4.7.2"
    // Picasso
    const val picasso = "2.71828"
    // Lottie
    const val lottie = "3.4.1"
    // Junit
    const val junit = "4.13"
    const val junitExt = "1.1.1"
    // Espresso
    const val espresso = "3.2.0"
}

object Dependencies {
    object Kotlin {
        const val standard = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.3.0"
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object LifeCycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    }

    // Fragment Navigation libraries:
    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val testing = "androidx.navigation:navigation-testing:${Versions.navigation}"
    }

    object MaterialDesign {
        const val material = "com.google.android.material:material:${Versions.materialDesign}"
    }

    object Firebase {
        const val analytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
        const val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlytics}"
    }

    // Koin dependency injection libraries:
    object Koin {
        const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val test = "org.koin:koin-test:${Versions.koin}"
    }

    // Retrofit API abstraction library:
    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    }

    // Picasso image downloading and cache library:
    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    }

    // Lottie animation library:
    object Lottie {
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val ext = "androidx.test.ext:junit:${Versions.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}
