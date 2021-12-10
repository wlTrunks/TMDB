package net.l1ngdtkh3.data.datasources.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val intId: Int = 0,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int>? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val type: MoviesType? = null,
    val isFavorite: Boolean? = null
)

@Entity
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val adult: Boolean?,
    val backdropPath: String?,
    val genres: List<GenreEntity>?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguageEntity>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

@Entity
data class MovieVideoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int?,
    val videos: List<VideoEntity>?
)

@Entity
data class CastEntity(
    @PrimaryKey(autoGenerate = false) val id: Int?,
    val actor: List<ActorEntity>?
)

data class VideoEntity(
    val id: String?,
    val iso31661: String?,
    val iso6391: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)

data class SpokenLanguageEntity(
    val englishName: String?,
    val iso6391: String?,
    val name: String?
)

data class GenreEntity(
    val id: Int?,
    val name: String?
)

data class ActorEntity(
    val castId: Int?,
    val character: String?,
    val creditId: String?,
    val id: Int?,
    val name: String?,
    val originalName: String?,
    val profilePath: String?
)

data class DatesEntity(
    val maximum: String?,
    val minimum: String?
)

enum class MoviesType {
    NOW_PLAYING, UP_COMING, POPULAR
}