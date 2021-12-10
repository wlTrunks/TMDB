package net.l1ngdtkh3.ui.extensions

/**
 * Append the image url with string to determine the image quality to be loaded
 */

const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/"
fun String.loadImage(): String {
    var imageQuality: String? = null
    when ("high_quality") {
        "high_quality" -> {
            imageQuality = "${IMAGE_PREFIX}/original/$this"
        }
        "medium_quality" -> {
            imageQuality = "${IMAGE_PREFIX}/w500/$this"
        }
        "low_quality" -> {
            imageQuality = "${IMAGE_PREFIX}/w500/$this" //ToDo: Lower image quality value
        }
        else -> imageQuality = "${IMAGE_PREFIX}/w500/$this"
    }
    return imageQuality!!
}
