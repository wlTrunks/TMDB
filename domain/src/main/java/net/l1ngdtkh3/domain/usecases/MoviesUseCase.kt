package net.l1ngdtkh3.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import net.l1ngdtkh3.domain.models.Movie
import net.l1ngdtkh3.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val repo: MoviesRepository
) {
    fun nowPlayingMoviesPaging(): Flow<PagingData<Movie>> = repo.pagingNowPlayingMovies()

    suspend fun addMovieToFavorites(id: Int, isFavorite: Boolean) {
        repo.addMovieToFavorites(id, isFavorite)
    }
}