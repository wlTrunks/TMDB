package net.l1ngdtkh3.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import net.l1ngdtkh3.domain.models.Movie

interface MoviesRepository {
    fun pagingPopularMovies(): Flow<PagingData<Movie>>
    fun pagingNowPlayingMovies(): Flow<PagingData<Movie>>
    suspend fun addMovieToFavorites(intId: Int, isFavorite: Boolean)
}