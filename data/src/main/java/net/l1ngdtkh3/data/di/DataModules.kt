package net.l1ngdtkh3.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.l1ngdtkh3.data.BuildConfig
import net.l1ngdtkh3.data.datasources.Common
import net.l1ngdtkh3.data.datasources.datastore.SettingsDataStore
import net.l1ngdtkh3.data.datasources.datastore.SettingsDataStoreImpl
import net.l1ngdtkh3.data.datasources.db.AppDatabase
import net.l1ngdtkh3.data.datasources.network.TMDBApi
import net.l1ngdtkh3.data.datasources.repository.MoviesRepositoryImpl
import net.l1ngdtkh3.domain.repository.MoviesRepository
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideService(httpClient: OkHttpClient): TMDBApi =
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(URL)
            .addConverterFactory(Common.json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TMDBApi::class.java)

    @Provides
    @Singleton
    fun requestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val authUrl =
                original.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_TOKEN).build()
            val requestBuilder = original.newBuilder()
                .url(authUrl)
                .header("Accept", "application/json")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun okHttpClient(requestInterceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        // add logging interceptor for DEBUG variants
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            client.addInterceptor(interceptor)
        }
        return client.build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "TMDB"
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {
    @Binds
    fun provideDataStore(dataStore: SettingsDataStoreImpl): SettingsDataStore
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideMoviesRepository(repo: MoviesRepositoryImpl): MoviesRepository
}