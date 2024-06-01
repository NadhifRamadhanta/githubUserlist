package com.example.androidsubmission1.core.Database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.androidsubmission1.core.domain.model.Favorite
import com.example.androidsubmission1.core.domain.repository.IFavoriteRepository
import com.example.androidsubmission1.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepository(
    private val favoriteDao: FavoriteDao,
    private val appExecutors: AppExecutors
) : IFavoriteRepository {

    override fun getFavorite(): Flow<List<Favorite>> {
        return favoriteDao.getFavorited()
    }

    override fun getUsername(username: String): Flow<Favorite> {
        Log.d("favoriterepo","${favoriteDao.getUsername(username).map{Log.d("tesfavrepo","$it")}}")
        Log.d("tesfavrepo","$username ")
        return favoriteDao.getUsername(username)
    }

    override fun delFavorite(favorite: Favorite) {
        appExecutors.diskIO.execute {
            favoriteDao.delete(favorite)
        }
    }

    override suspend fun setFavorite(favorite: Favorite, favoriteState: Boolean) {
            favorite.isFavorite = favoriteState
            favoriteDao.insert(favorite)
    }

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null
//        fun getInstance(
//            favoriteDao: FavoriteDao,
//            appExecutors: AppExecutors
//        ): FavoriteRepository =
//            instance ?: synchronized(this) {
//                instance ?: FavoriteRepository(favoriteDao, appExecutors)
//            }.also { instance = it }
    }

}