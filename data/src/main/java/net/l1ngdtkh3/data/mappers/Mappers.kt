package net.l1ngdtkh3.data.mappers

import net.l1ngdtkh3.data.datasources.Common.parseDate
import net.l1ngdtkh3.data.datasources.db.MovieEntity
import net.l1ngdtkh3.data.datasources.db.MoviesType
import net.l1ngdtkh3.data.datasources.network.models.MovieDTO
import net.l1ngdtkh3.domain.models.Movie

internal fun MovieEntity.toDomain(): Movie = Movie(
    id = id,
    intId = intId,
    title = title,
    posterPath = posterPath,
    isFavorite = isFavorite ?: false,
    releaseDate = releaseDate?.parseDate(),
    voteAverage = voteAverage ?: 0.0
)

internal fun MovieDTO.toEntity(type: MoviesType? = null): MovieEntity {
    return MovieEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        type = type,
        isFavorite = false
    )
}