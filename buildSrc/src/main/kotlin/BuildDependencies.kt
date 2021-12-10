import org.gradle.api.JavaVersion

/**
 * File contain versions dependencies and constants full path dependencies
 */
object Versions {
    val JAVA = JavaVersion.VERSION_11
    const val GRADLE = "7.0.4"
    const val KOTLIN = "1.6.0"
    const val CORE_KTX = "1.6.0"
    const val APPCOMPAT = "1.3.1"
    const val MATERIAL = "1.4.0"
    const val CONSTRAINT_LAYOUT = "2.1.1"
    const val RECYCLER_VIEW = "1.2.1"
    const val FRAGMENT = "1.4.0-alpha09"
    const val LIFECYCLE = "2.4.0-rc01"
    const val LIFECYCLE_EXT = "2.2.0"
    const val COROUTINES = "1.5.2"
    const val ROOM = "2.4.0-alpha04"
    const val PAGING = "3.0.1"
    const val DATASTORE = "1.0.0"
    const val NAVIGATION = "2.3.5"
    const val HILT = "2.38.1"
    const val HILT_VIEW_MODEL = "1.0.0-alpha03"
    const val HILT_COMPILER = "1.0.0"
    const val DAGGER = "2.39.1"
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.9.1"
    const val COIL = "1.3.2"
    const val COIL_TRANSFORMER = "1.0.3"
    const val EXO_PLAYER = "2.15.0"
    const val SERIALIZATION = "1.3.0"
    const val SERIALIZATION_CONVERTER = "0.8.0"

    const val JUNIT = "4.13.2"
    const val ROBOELECTRIC = "4.5.1"
    const val ESPRESSO = "3.3.0"
    const val FRAGMENT_TEST = "1.3.6"
    const val ANDROIDX_TEST = "1.4.0"
    const val COROUTINE_TEST = "1.4.3"
    const val ARCH_TEST_CORE = "2.1.0"
    const val MOCKK = "1.12.0"
    const val EXT = "1.1.3"
    const val ASSERTJ = "3.17.2"
    const val TEST = "1.3.0"
    const val MOCK_WEB_SERVER = "4.9.1"
    const val TIMBER = "5.0.1"
}

object DependenciesKapt {
    const val ROOM = "androidx.room:room-compiler:${Versions.ROOM}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val DAGGER_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
}

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXT}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"
    const val DATABINDING = "androidx.databinding:viewbinding:${Versions.GRADLE}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val PAGING = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"
    const val DATASTORE_PREFERENCES = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
    const val DATASTORE = "androidx.datastore:datastore:${Versions.DATASTORE}"
    const val DATASTORE_CORE = "androidx.datastore:datastore-core:${Versions.DATASTORE}"
    const val HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEW_MODEL}"
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val RETROFIT_CONVERTER =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.SERIALIZATION_CONVERTER}"
    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION}"
    const val SERIALIZATION_CORE = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.SERIALIZATION}"
}

object TestAndroidDependencies {
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val RUNNER = "androidx.test:runner:${Versions.TEST}"
    const val RULES = "androidx.test:rules:${Versions.TEST}"
    const val JUNIT = "androidx.test.ext:junit:${Versions.EXT}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${Versions.FRAGMENT_TEST}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val ASSERTJ = "org.assertj:assertj-core:${Versions.ASSERTJ}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${Versions.ROBOELECTRIC}"
    const val ROOM = "androidx.room:room-testing:${Versions.ROOM}"
    const val EXT = "androidx.test.ext:junit:${Versions.EXT}"
    const val CORE = "androidx.test:core:${Versions.TEST}"
    const val RUNNER = "androidx.test:runner:${Versions.TEST}"
    const val RULES = "androidx.test:rules:${Versions.TEST}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${Versions.ARCH_TEST_CORE}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${Versions.FRAGMENT_TEST}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEB_SERVER}"
}
