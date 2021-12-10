package net.l1ngdtkh3.data.datasources.network.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * DTO classes TMDB API https://developers.themoviedb.org/3/movies/get-movie-details
 * There is a few of them fo locate it on file
 */

@Serializable
class MovieDTO(
    val adult: Boolean?,
    @JsonNames("backdrop_path") val backdropPath: String?,
    @JsonNames("genre_ids") val genreIds: List<Int>?,
    val id: Int?,
    @JsonNames("original_language") val originalLanguage: String?,
    @JsonNames("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @JsonNames("poster_path") val posterPath: String?,
    @JsonNames("release_date") val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @JsonNames("vote_average") val voteAverage: Double?,
    @JsonNames("vote_count") val voteCount: Int?
)

@Serializable
data class NowPlayingMoviesDTO(
    val dates: DatesDTO?,
    val page: Int?,
    @JsonNames("results") val movies: List<MovieDTO>?,
    @JsonNames("total_pages") val totalPages: Int?,
    @JsonNames("total_results") val totalResults: Int?
)

@Serializable
data class UpcomingMoviesDTO(
    val dates: DatesDTO?,
    val page: Int?,
    @JsonNames("results") val movies: List<MovieDTO>?,
    @JsonNames("total_pages") val totalPages: Int?,
    @JsonNames("total_results") val totalResults: Int?
)

@Serializable
data class MovieDetailsDTO(
    val adult: Boolean?,
    @JsonNames("backdrop_path") val backdropPath: String?,
    val genres: List<GenreDTO>?,
    val homepage: String?,
    val id: Int?,
    @JsonNames("imdb_id") val imdbId: String?,
    @JsonNames("original_language") val originalLanguage: String?,
    @JsonNames("original_title") val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @JsonNames("poster_path") val posterPath: String?,
    @JsonNames("release_date") val releaseDate: String?,
    val runtime: Int?,
    @JsonNames("spoken_languages") val spokenLanguages: List<SpokenLanguageDto>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @JsonNames("vote_average") val voteAverage: Double?,
    @JsonNames("vote_count") val voteCount: Int?
)

@Serializable
data class MovieVideoDTO(
    val id: Int?,
    @JsonNames("results") val videos: List<VideoDTO>?
)

@Serializable
data class VideoDTO(
    val id: String?,
    @JsonNames("iso_3166_1") val iso31661: String?,
    @JsonNames("iso_639_1") val iso6391: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    @JsonNames("published_at") val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)

@Serializable
data class GenreDTO(
    val id: Int?,
    val name: String?
)

@Serializable
data class SpokenLanguageDto(
    @JsonNames("english_name") val englishName: String?,
    @JsonNames("iso_639_1") val iso6391: String?,
    val name: String?
)

@Serializable
data class DatesDTO(
    val maximum: String?,
    val minimum: String?
)

