plugins {
    id("library_android")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_COMMON)
    implementation(Dependencies.DATABINDING)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.MATERIAL)
}