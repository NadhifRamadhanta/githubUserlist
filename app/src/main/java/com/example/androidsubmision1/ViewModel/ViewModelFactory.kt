//package com.example.androidsubmision1.ViewModel
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.androidsubmision1.Database.FavoriteRepository
//import com.example.androidsubmision1.Database.Injection
//import com.example.androidsubmision1.domain.usecase.FavoriteUseCase
//
//class ViewModelFactory private constructor(private val favoriteUseCase: FavoriteUseCase) :
//    ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
//            return FavoriteViewModel(favoriteUseCase) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(Injection.provideUseCase(context))
//            }.also { instance = it }
//    }
//}