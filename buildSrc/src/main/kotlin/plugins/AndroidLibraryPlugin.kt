package plugins

import AndroidConfig
import BuildPlugins
import Dependencies
import DependenciesKapt
import Versions
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidLibraryPlugin : Plugin<Project>, BasePlugin {

    override fun apply(target: Project) {
        target.run {
            applyPlugins {
                it.apply {
                    apply(BuildPlugins.ANDROID_LIBRARY)
                    apply(BuildPlugins.KOTLIN_ANDROID)
                    apply(BuildPlugins.KOTLIN_KAPT)
                    apply(BuildPlugins.SERIALIZATION)
                }
            }
            applyAndroidLibraryExtension()
            applyJavaExtension()
            importExternalLibs {
                it.apply {
                    implementation(Dependencies.HILT)
                    implementation(Dependencies.TIMBER)

                    kapt(DependenciesKapt.HILT)
                    kapt(DependenciesKapt.HILT_ANDROID)
                }
            }
        }
    }

    /**
     * Configures the [Android library][com.android.build.api.dsl.LibraryExtension] extension.
     */
    private fun Project.applyAndroidLibraryExtension() {
        val extension = project.extensions.findByType(LibraryExtension::class.java) ?: return
        val kotlinOption =
            (extension as org.gradle.api.plugins.ExtensionAware).extensions.getByName("kotlinOptions") as KotlinJvmOptions
        kotlinOption.jvmTarget = Versions.JAVA.toString()
        extension.apply {
            compileSdk = AndroidConfig.COMPILE_SDK_VERSION
            buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION
            defaultConfig {
                minSdk = AndroidConfig.MIN_SDK_VERSION
                targetSdk = AndroidConfig.TARGET_SDK_VERSION
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            compileOptions {
                sourceCompatibility = Versions.JAVA
                targetCompatibility = Versions.JAVA
            }
        }
    }
}

