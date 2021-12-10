plugins {
    id("library_android")
    id(BuildPlugins.HILT)
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.PAGING)
}