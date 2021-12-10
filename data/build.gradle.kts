import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("library_android")
    id(BuildPlugins.HILT)
}

android {
    val localProperties = Properties()
    localProperties.load(FileInputStream(rootProject.file("local.properties")))
    defaultConfig {
        buildConfigField("String", "API_TOKEN", localProperties.getProperty("API_TOKEN"))
    }
}

dependencies {
    implementation(project(BuildModules.DOMAIN))
    implementation(project(BuildModules.Common.TEST_LIB))
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.PAGING)
    implementation(Dependencies.SERIALIZATION_CORE)
    implementation(Dependencies.SERIALIZATION_JSON)
    implementation(Dependencies.DATASTORE_PREFERENCES)
    implementation(Dependencies.DATASTORE)
    implementation(Dependencies.DATASTORE_CORE)

    // Room
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(DependenciesKapt.ROOM)
    // Retrofit & Networking
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
}