package net.l1ngdtkh3.domain.models

data class Movie(
    val id: Int?,
    val intId: Int,
    val title: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val isFavorite: Boolean
)