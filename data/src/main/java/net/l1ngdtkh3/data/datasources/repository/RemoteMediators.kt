package net.l1ngdtkh3.data.datasources.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import net.l1ngdtkh3.data.datasources.datastore.LastTimeUpdate
import net.l1ngdtkh3.data.datasources.db.AppDatabase
import net.l1ngdtkh3.data.datasources.db.MovieEntity
import net.l1ngdtkh3.data.datasources.db.MoviesType
import net.l1ngdtkh3.data.datasources.network.TMDBApi
import net.l1ngdtkh3.data.mappers.toEntity
import timber.log.Timber
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
internal class MovieRemoteMediator(
    private val type: MoviesType,
    private val syncTimeNowPlay: LastTimeUpdate,
    private val service: TMDBApi,
    private val db: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {

    private val moviesDao = db.moviesDao()
    private var page = 1

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MINUTES.convert(1, TimeUnit.MILLISECONDS)
        val lastTime = syncTimeNowPlay.getLastTimeUpdate(type)
        val skipRefresh = System.currentTimeMillis() - lastTime >= cacheTimeout
        Timber.d("lastTime $lastTime")
        Timber.d("Need skip refresh $skipRefresh")
        return if (lastTime != 0L && skipRefresh) { //for now
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            syncTimeNowPlay.setLastTimeUpdate(type, System.currentTimeMillis())
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> page = 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem =
                        state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
            }
            Timber.d("state config ${state.anchorPosition}")
            val response = service.nowPlayingMovies(page, "en").run {
                body()?.let {
                    Timber.d("page ${it.page}")
                    Timber.d("movies size ${it.movies?.size}")
                    Timber.d("dates ${it.dates}")
                    db.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            moviesDao.deleteMovies(type)
                        }
                        it.movies?.map { it.toEntity(type) }?.let { moviesDao.saveMovies(it) }
                    }
                    page += 1
                }
            }
            MediatorResult.Success(endOfPaginationReached = response == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
