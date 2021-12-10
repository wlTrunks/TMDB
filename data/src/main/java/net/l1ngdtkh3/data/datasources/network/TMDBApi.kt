package net.l1ngdtkh3.data.datasources.network

import net.l1ngdtkh3.data.datasources.network.models.MovieDetailsDTO
import net.l1ngdtkh3.data.datasources.network.models.MovieVideoDTO
import net.l1ngdtkh3.data.datasources.network.models.NowPlayingMoviesDTO
import net.l1ngdtkh3.data.datasources.network.models.UpcomingMoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<NowPlayingMoviesDTO>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Response<UpcomingMoviesDTO>

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<MovieDetailsDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun movieVideos(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<MovieVideoDTO>
}