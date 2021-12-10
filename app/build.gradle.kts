plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.SERIALIZATION)
    id(BuildPlugins.HILT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    signingConfigs {
        getByName("debug") {
            keyAlias = "debug"
            keyPassword = "123456"
            storeFile = file("../keystore.jks")
            storePassword = "123456"
        }
    }
    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = Versions.JAVA.toString()
    }
    buildFeatures {
        viewBinding = true
    }
    setDynamicFeatures(mutableSetOf(":features:favorite"))
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableExperimentalClasspathAggregation = true
    }
    packagingOptions {
        resources.pickFirsts.apply {
            //https://issuetracker.google.com/issues/170131605
            add("win32-x86/attach_hotspot_windows.dll")
            add("win32-x86-64/attach_hotspot_windows.dll")
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
            add("META-INF/licenses/ASM")
        }
    }
}

dependencies {
    implementation(project(BuildModules.Common.UI))
    implementation(project(BuildModules.Features.FEED))

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_COMMON)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COIL)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_DYNAMIC)
    implementation(Dependencies.TIMBER)

    // Hilt
    implementation(Dependencies.HILT)
    implementation(Dependencies.HILT_VIEW_MODEL)
    kapt(DependenciesKapt.HILT_ANDROID)
    kapt(DependenciesKapt.HILT)
}