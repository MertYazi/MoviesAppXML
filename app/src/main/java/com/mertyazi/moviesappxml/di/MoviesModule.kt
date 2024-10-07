package com.mertyazi.moviesappxml.di

import android.content.Context
import androidx.room.Room
import com.mertyazi.moviesappxml.core.util.Constants.BASE_URL
import com.mertyazi.moviesappxml.details.data.api.DetailsApi
import com.mertyazi.moviesappxml.details.data.repository.DetailsRepositoryImp
import com.mertyazi.moviesappxml.details.domain.repository.DetailsRepository
import com.mertyazi.moviesappxml.core.data.db.FavoritesDao
import com.mertyazi.moviesappxml.core.domain.db.FavoritesDatabase
import com.mertyazi.moviesappxml.core.data.repository.FavoritesRepositoryImp
import com.mertyazi.moviesappxml.core.domain.repository.FavoritesRepository
import com.mertyazi.moviesappxml.movies.data.api.MoviesApi
import com.mertyazi.moviesappxml.movies.data.repository.MoviesRepositoryImp
import com.mertyazi.moviesappxml.movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Mert Yazi
 */

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepositoryImp(
        moviesApi: MoviesApi
    ): MoviesRepository {
        return MoviesRepositoryImp(moviesApi)
    }

    @Provides
    @Singleton
    fun providesDetailsApi(): DetailsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(DetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailsRepositoryImp(
        detailsApi: DetailsApi
    ): DetailsRepository {
        return DetailsRepositoryImp(detailsApi)
    }

    @Provides
    @Singleton
    fun providesFavoritesRepository(
        repository: FavoritesRepositoryImp
    ): FavoritesRepository = repository

    @Provides
    @Singleton
    fun providesFavoritesRepositoryImp(dao: FavoritesDao): FavoritesRepositoryImp {
        return FavoritesRepositoryImp(dao)
    }

    @Provides
    @Singleton
    fun providesFavoritesDao(
        @ApplicationContext context: Context
    ): FavoritesDao {
        val db = Room.databaseBuilder(
            context, FavoritesDatabase::class.java,
            "favorites-db"
        ).build()
        return db.favoritesDao()
    }

}