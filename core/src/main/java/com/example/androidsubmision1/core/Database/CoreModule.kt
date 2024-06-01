package com.example.androidsubmission1.core.Database

import androidx.room.Room
import com.example.androidsubmision1.ApiService
import com.example.androidsubmission1.core.BuildConfig

//import com.example.androidsubmision1.MainViewModel
//import com.example.androidsubmision1.ViewModel.FavoriteViewModel
import com.example.androidsubmission1.core.domain.repository.IFavoriteRepository
import com.example.androidsubmission1.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory(override = true) { get<FavoriteRoomDatabase>().favoriteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteRoomDatabase::class.java, "favoriteUser"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()
    }

    single {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.URL).addConverterFactory(GsonConverterFactory.create()).client(get()).build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { FavoriteRepository(get(),get()) }
//    single { FavoriteViewModel(get())}
    factory(override = true) { AppExecutors() }
    single<IFavoriteRepository> { FavoriteRepository(get(),get()) }
}