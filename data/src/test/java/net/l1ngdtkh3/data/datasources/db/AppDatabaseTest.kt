package net.l1ngdtkh3.data.datasources.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.room.Room
import kotlinx.coroutines.runBlocking
import net.l1ngdtkh3.testlib.CoroutineTestRule
import net.l1ngdtkh3.testlib.TestRobolectric
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppDatabaseTest : TestRobolectric() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var moviesDao: MoviesDao

    @Before
    fun setUp() {
        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        moviesDao = database.moviesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `save movies to db`() = runBlocking {
        val moviesList = mockMovies(MoviesType.POPULAR)
        moviesDao.saveMovies(moviesList)
        val load = moviesDao.getMovies(MoviesType.POPULAR).load(refresh())
        assert((load as PagingSource.LoadResult.Page).data.size == 4)
    }

    @Test
    fun `get paging by Movies type`() = runBlocking {
        val moviesList = mockMovies(MoviesType.POPULAR) + mockMovies(MoviesType.UP_COMING)
        moviesDao.saveMovies(moviesList)
        val load = moviesDao.getMovies(MoviesType.POPULAR).load(refresh())
        assert((load as PagingSource.LoadResult.Page).data.size == 4)
    }

    @Test
    fun `get favorite movies`() = runBlocking {
        val moviesList = mockMoviesWithFavorites()
        moviesDao.saveMovies(moviesList)
        val load = moviesDao.getFavoriteMovies().load(refresh())
        assert((load as PagingSource.LoadResult.Page).data.size == 2)
    }

    @Test
    fun `set movie favorite`() = runBlocking {
        val moviesList = mockMovies(MoviesType.POPULAR)
        moviesDao.saveMovies(moviesList)
        val movie = (moviesDao.getMovies(MoviesType.POPULAR).load(refresh()) as PagingSource.LoadResult.Page).data[2]
        moviesDao.updateMovieIsFavorite(movie.intId, true)
        val loadFavorites = moviesDao.getFavoriteMovies().load(refresh()) as PagingSource.LoadResult.Page
        assert(loadFavorites.data.size == 1)
        assert(loadFavorites.data[0].intId == movie.intId)
    }

    @Test
    fun `delete movies by Movies type, favorites movies saved`() = runBlocking {
        val moviesList = mockMoviesWithFavorites()
        moviesDao.saveMovies(moviesList)
        moviesDao.deleteMovies(MoviesType.POPULAR)
        val load = moviesDao.getMovies(MoviesType.POPULAR).load(refresh()) as PagingSource.LoadResult.Page
        assert(load.data.size == 1)
    }

    @Test
    fun `delete movies by Movies type, favorites movies deleted`() = runBlocking {
        val moviesList = mockMoviesWithFavorites()
        moviesDao.saveMovies(moviesList)
        moviesDao.deleteMovies(MoviesType.POPULAR, false)
        val load = moviesDao.getMovies(MoviesType.POPULAR).load(refresh())
        assert((load as PagingSource.LoadResult.Page).data.isEmpty())
    }

    private fun mockMoviesWithFavorites(): MutableList<MovieEntity> {
        val moviesList = mockMovies(MoviesType.POPULAR).apply {
            addAll(
                listOf(
                    MovieEntity(
                        id = 6,
                        title = "6",
                        type = MoviesType.POPULAR,
                        isFavorite = true
                    ),
                    MovieEntity(
                        id = 7,
                        title = "7",
                        type = MoviesType.NOW_PLAYING,
                        isFavorite = true
                    )
                )
            )
        }
        return moviesList
    }

    private fun refresh(): PagingSource.LoadParams.Refresh<Int> = PagingSource.LoadParams.Refresh(
        key = null,
        loadSize = 10,
        placeholdersEnabled = false
    )

    private fun mockMovies(type: MoviesType, startId: Int = 0, endId: Int = 3): MutableList<MovieEntity> {
        val moviesList = mutableListOf<MovieEntity>()
        for (i in startId..endId) {
            moviesList.add(
                MovieEntity(
                    id = i,
                    title = "moview$i",
                    type = type
                )
            )
        }
        return moviesList
    }
}