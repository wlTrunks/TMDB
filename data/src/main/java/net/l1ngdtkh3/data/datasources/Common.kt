package net.l1ngdtkh3.data.datasources

import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat

object Common {
    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    val originalFormat = SimpleDateFormat("yyyy-MM-dd")
    val releaseFormat = SimpleDateFormat("dd MMM yyyy")

    fun String.parseDate(): String = releaseFormat.format(originalFormat.parse(this))
}
