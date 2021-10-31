object BuildPlugins {
    object Versions {
        const val gradle = "7.0.2"
        const val kotlin = "1.5.31"
        const val hilt = "2.39.1"
    }


    const val androidGradleDependency = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradleDependency =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradleDependency = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object AndroidSdk {
    const val minSdk = 21
    const val compileSdk = 30
    const val targetSdk = 30
}

object AndroidApplication {
    const val appId = "com.medhdj.highlights"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}


object Libraries {
    private object Versions {
        const val kotlinKtx = "1.6.0"

        const val rxJava = "2.2.2"
        const val rxJavaAndroid = "2.1.0"
        const val rxKotlinVersion = "2.0.0"

        const val hilt = BuildPlugins.Versions.hilt

        const val retrofit = "2.9.0"
        const val okhttp = "4.7.2"

        const val appCompat = "1.3.1"
        const val materialDesign = "1.4.0"
        const val constraintLayout = "2.1.1"
        const val navigation = "2.3.5"
        const val lifeCycle = "2.3.1"
        const val fragmentKtx = "1.3.6"

        const val exoplayer = "2.15.1"

        const val timber = "4.7.1"
    }


    // Kotlin
    const val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.kotlinKtx}"

    // RxJava
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxJavaAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"

    // DI
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"


    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okHttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // UI
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // Player
    const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
    const val exoplayerUI = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"
    const val exoplayerDash = "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer}"



    // utils
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    private object Versions {
        const val junit = "4.12"
        const val kluent = "1.68"
        const val mockk = "1.12.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val kluent = "io.mockk:mockk:${Versions.mockk}"
}
