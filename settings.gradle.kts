rootProject.name = "TMDB"
include(
    ":app",
    ":data",
    ":domain",
    ":common:ui",
    ":features:feed",
    ":features:favorite"
)
rootProject.buildFileName = "build.gradle.kts"
include(":common:testlib")
