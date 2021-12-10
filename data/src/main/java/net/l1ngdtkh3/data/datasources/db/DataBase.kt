package net.l1ngdtkh3.data.datasources.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class, MovieVideoEntity::class, CastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    GenreIDEntityConverter::class,
    GenreEntityConverter::class,
    SpokenLanguageEntityConverter::class,
    ActorEntityConverter::class,
    VideoEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    // abstract fun movieDetailsDao(): MovieDetailsDao
    // abstract fun castDao(): CastDao
    // abstract fun videosDao(): VideosDao
}

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE type=:type")
    fun getMovies(type: MoviesType): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM MovieEntity WHERE type=:type AND isFavorite IS NOT :isFavorite")
    suspend fun deleteMovies(type: MoviesType, isFavorite: Boolean = true)

    @Query("SELECT * FROM MovieEntity WHERE isFavorite = 1")
    fun getFavoriteMovies(): PagingSource<Int, MovieEntity>

    @Query("UPDATE MovieEntity SET isFavorite=:isFavorite WHERE intId=:intId")
    suspend fun updateMovieIsFavorite(intId: Int, isFavorite: Boolean)
}