import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("library_android") {
            id = "library_android"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("library_android_dynamic_feature") {
            id = "library_android_dynamic_feature"
            implementationClass = "plugins.AndroidDynamicFeatureLibraryPlugin"
        }
        register("library_android_feature") {
            id = "library_android_feature"
            implementationClass = "plugins.AndroidFeatureLibraryPlugin"
        }
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = JavaVersion.VERSION_11.toString()
}

object PluginVersion {
    const val GRADLE = "7.0.4"
    const val KOTLIN = "1.6.0"
    const val HILT = "2.38.1"
    const val BUNDLE_TOOL = "1.8.1"
}

/**
 * This should be in-sync with the Gradle version exposed by [Versions]
 */
dependencies {
    implementation("com.android.tools.build:gradle:${PluginVersion.GRADLE}")
    implementation("com.android.tools.build:gradle-api:${PluginVersion.GRADLE}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginVersion.HILT}")
    implementation("com.android.tools.build:bundletool:${PluginVersion.BUNDLE_TOOL}")
    implementation(kotlin("stdlib"))
    gradleApi()
}