package com.example.androidsubmission1.core.Database

import android.content.Context
import com.example.androidsubmission1.core.domain.repository.IFavoriteRepository
import com.example.androidsubmission1.core.domain.usecase.FavoriteInteractor
import com.example.androidsubmission1.core.domain.usecase.FavoriteUseCase
import com.example.androidsubmission1.core.utils.AppExecutors

object Injection {
//    fun provideRepository(context: Context): IFavoriteRepository {
//        val database = FavoriteRoomDatabase.getDatabase(context)
//        val dao = database.favoriteDao()
//        val appExecutors = AppExecutors()
//        return FavoriteRepository.getInstance(dao, appExecutors)
//    }
//
//    fun provideUseCase(context: Context) : FavoriteUseCase{
//        val repository = provideRepository(context)
//        return FavoriteInteractor(repository)
//    }
}