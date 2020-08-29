package dependencies

object Dependencies {
    private const val path = "../commonFiles/gradleScript/"
    const val common = "${path}common.gradle"

    //path to local dependencies
    const val dependency = "./gradleScript/dependencies.gradle"

    object Module {
        const val domain = ":domain"
    }

    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Lint {
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detektVersion}"
        const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"
    }

    object Plugins {
        const val DETEKT = "io.gitlab.arturbosch.detekt"
    }

    object Test {
        const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"
        const val androidXEspresso =
            "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
        const val junit = "junit:junit:${Versions.jUnit}"
    }
}
