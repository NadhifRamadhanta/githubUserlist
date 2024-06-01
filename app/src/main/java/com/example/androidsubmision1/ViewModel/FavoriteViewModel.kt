package com.example.androidsubmision1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidsubmission1.core.domain.model.Favorite
import com.example.androidsubmission1.core.domain.usecase.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getFavorite() = favoriteUseCase.getFavorite().asLiveData()

    fun getUsername(username: String) = favoriteUseCase.getUsername(username).asLiveData()

    suspend fun saveUserFavorite(favorite: Favorite) {
        favoriteUseCase.setFavorite(favorite, true)
    }

    fun delete(favorite: Favorite) {
        favoriteUseCase.delFavorite(favorite)
    }


}