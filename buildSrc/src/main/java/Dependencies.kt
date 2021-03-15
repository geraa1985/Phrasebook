import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.geraa1985.phrasebook"
    const val compile_sdk = 30
    const val build_tools = "30.0.3"
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
    const val kotlin_options = "1.8"
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val model = ":model"
    const val cache = ":cache"
}


object BaseDependencies {
    const val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.31"
    const val core_ktx = "androidx.core:core-ktx:1.3.2"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
}

object Design {
    const val material = "com.google.android.material:material:1.3.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
}

object UnitTest {
    const val junit = "junit:junit:4.13.2"
    const val mockk = "io.mockk:mockk:1.10.6"
}

object UiTests {
    const val junit = "androidx.test.ext:junit:1.1.2"
    const val espresso_core = "androidx.test.espresso:espresso-core:3.3.0"
    const val espresso_intents = "androidx.test.espresso:espresso-intents:3.3.0"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:3.3.0"
}

object Moxy {
    const val moxy = "com.github.moxy-community:moxy:2.2.0"
    const val compiler = "com.github.moxy-community:moxy-compiler:2.2.0"
    const val ktx = "com.github.moxy-community:moxy-ktx:2.2.0"
    const val androidx = "com.github.moxy-community:moxy-androidx:2.2.0"
}

object Cicerone {
    const val cicerone = "ru.terrakok.cicerone:cicerone:5.1.1"
}

object OkHttp {
    const val okhttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val converter = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:4.12.0"
    const val compiler = "com.github.bumptech.glide:compiler:4.12.0"
}

object Room {
    const val runtime = "androidx.room:room-runtime:2.2.6"
    const val compiler = "androidx.room:room-compiler:2.2.6"
    const val ktx = "androidx.room:room-ktx:2.2.6"
}

object Koin {
    const val koin_android = "org.koin:koin-android:2.0.1"
    const val koin_android_viewmodel = "org.koin:koin-android-viewmodel:2.0.1"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1"
}