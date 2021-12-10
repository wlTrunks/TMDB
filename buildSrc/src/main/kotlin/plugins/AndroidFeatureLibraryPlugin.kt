package plugins

import AndroidConfig
import BuildPlugins
import Dependencies
import Versions
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidFeatureLibraryPlugin : Plugin<Project>, BasePlugin {

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
                    implementation(project(BuildModules.Common.UI))
                    implementation(project(BuildModules.DOMAIN))
                    implementation(project(BuildModules.DATA))
                    implementation(Dependencies.KOTLIN)
                    implementation(Dependencies.CORE_KTX)
                    implementation(Dependencies.COROUTINES)
                    implementation(Dependencies.COROUTINES_ANDROID)
                    implementation(Dependencies.APPCOMPAT)
                    implementation(Dependencies.HILT)
                    implementation(Dependencies.NAVIGATION_FRAGMENT)
                    implementation(Dependencies.NAVIGATION_UI)
                    implementation(Dependencies.CONSTRAINT_LAYOUT)
                    implementation(Dependencies.FRAGMENT_KTX)
                    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
                    implementation(Dependencies.LIFECYCLE_COMMON)
                    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
                    implementation(Dependencies.RECYCLE_VIEW)
                    implementation(Dependencies.MATERIAL)
                    implementation(Dependencies.COIL)
                    implementation(Dependencies.PAGING)
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
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            buildFeatures {
                viewBinding = true
            }
            compileOptions {
                sourceCompatibility = Versions.JAVA
                targetCompatibility = Versions.JAVA
            }
        }
    }
}

