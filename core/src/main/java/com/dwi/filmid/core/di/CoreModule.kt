package com.dwi.filmid.core.di

import androidx.room.Room
import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.data.source.MoviesRepository
import com.dwi.filmid.core.data.source.local.LocalDataSource
import com.dwi.filmid.core.data.source.local.room.MovieDatabase
import com.dwi.filmid.core.data.source.remote.RemoteDataSource
import com.dwi.filmid.core.data.source.remote.network.ApiService
import com.dwi.filmid.core.domain.repository.IMoviesRepository
import com.dwi.filmid.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "filmid.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single { MoviesRepository(get(), get(), get()) } bind IMoviesRepository::class
}
