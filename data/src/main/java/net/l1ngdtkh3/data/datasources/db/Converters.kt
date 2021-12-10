package net.l1ngdtkh3.data.datasources.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenreIDEntityConverter {
    @TypeConverter
    fun from(data: List<Int>?): String? {
        if (data.isNullOrEmpty()) return null
        return Json.encodeToString(data)
    }

    @TypeConverter
    fun to(data: String?): List<Int>? {
        if (data.isNullOrEmpty()) return null
        return Json.decodeFromString<List<Int>>(data)
    }
}

class GenreEntityConverter {
    @TypeConverter
    fun from(data: List<GenreEntity>?): String? {
        if (data.isNullOrEmpty()) return null
        return Json.encodeToString(data)
    }

    @TypeConverter
    fun to(data: String?): List<GenreEntity>? {
        if (data.isNullOrEmpty()) return null
        return Json.decodeFromString<List<GenreEntity>>(data)
    }
}

class SpokenLanguageEntityConverter {
    @TypeConverter
    fun from(data: List<SpokenLanguageEntity>?): String? {
        if (data.isNullOrEmpty()) return null
        return Json.encodeToString(data)
    }

    @TypeConverter
    fun to(data: String?): List<SpokenLanguageEntity>? {
        if (data.isNullOrEmpty()) return null
        return Json.decodeFromString<List<SpokenLanguageEntity>>(data)
    }
}

@ProvidedTypeConverter
class VideoEntityConverter {
    @TypeConverter
    fun from(data: List<VideoEntity>?): String? {
        if (data.isNullOrEmpty()) return null
        return Json.encodeToString(data)
    }

    @TypeConverter
    fun to(data: String?): List<VideoEntity>? {
        if (data.isNullOrEmpty()) return null
        return Json.decodeFromString<List<VideoEntity>>(data)
    }
}

class ActorEntityConverter {
    @TypeConverter
    fun from(data: List<ActorEntity>?): String? {
        if (data.isNullOrEmpty()) return null
        return Json.encodeToString(data)
    }

    @TypeConverter
    fun to(data: String?): List<ActorEntity>? {
        if (data.isNullOrEmpty()) return null
        return Json.decodeFromString<List<ActorEntity>>(data)
    }
}