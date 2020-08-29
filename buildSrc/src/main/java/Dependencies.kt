package dependencies

object Dependencies {
    private const val path = "../commonFiles/gradleScript/"
    const val common = "${path}common.gradle"
    const val dependency = "./gradleScript/dependencies.gradle"

    object Module {
        const val domain = ":domain"
        const val remote = ":remote"
        const val data = ":data"
    }

    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataScope}"
    }

    object Lint {
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detektVersion}"
        const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"
    }

    object Dagger {
        const val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger2}"
        const val daggerAndroidSupport =
            "com.google.dagger:dagger-android-support:${Versions.dagger2}"
        const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger2}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    }

    object Hilt {
        const val main = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val compiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    }

    object Retrofit {
        const val main = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Facebook {
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stethoNetwork = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    }

    object Test {
        const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"
        const val androidXEspresso =
            "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
        const val junit = "junit:junit:${Versions.jUnit}"
    }

    const val javax = "javax.inject:javax.inject:${Versions.javaxInject}"
    const val javaxjsr250 = "javax.annotation:jsr250-api:${Versions.javaxAnnotation}"
}
