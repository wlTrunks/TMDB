package net.l1ngdtkh3.data.datasources.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import net.l1ngdtkh3.data.datasources.datastore.SettingsDataStore
import net.l1ngdtkh3.data.datasources.db.AppDatabase
import net.l1ngdtkh3.data.datasources.db.MoviesType
import net.l1ngdtkh3.data.datasources.network.TMDBApi
import net.l1ngdtkh3.data.mappers.toDomain
import net.l1ngdtkh3.domain.models.Movie
import net.l1ngdtkh3.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val service: TMDBApi,
    private val dataStore: SettingsDataStore
) : MoviesRepository {

    override fun pagingPopularMovies(): Flow<PagingData<Movie>> = emptyFlow()

    @OptIn(ExperimentalPagingApi::class)
    override fun pagingNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(MoviesType.NOW_PLAYING, dataStore, service, db),
            pagingSourceFactory = {
                db.moviesDao().getMovies(MoviesType.NOW_PLAYING)
            }
        ).flow.map { it.map { it.toDomain() } }
    }

    override suspend fun addMovieToFavorites(intId: Int, isFavorite: Boolean) {
        db.moviesDao().updateMovieIsFavorite(intId, isFavorite)
    }
}