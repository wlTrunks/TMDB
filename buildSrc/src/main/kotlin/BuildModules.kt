/**
 * Modules of project
 */
object BuildModules {
    const val APP = ":app"
    const val DATA = ":data"
    const val DOMAIN = ":domain"

    object Features {
        const val FEED = ":features:feed"
        const val FAVORITE = ":features:favorite"
    }

    object Common {
        const val UI = ":common:ui"
        const val TEST_LIB = ":common:testlib"
    }
}