package com.example.androidsubmision1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsubmision1.ApiConfig
import com.example.androidsubmision1.ItemsItem
import com.example.androidsubmision1.ProfileResponse
import com.example.androidsubmission1.core.API.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel() {

    private val _detailUser = MutableLiveData<ProfileResponse>()
    val detailUser: LiveData<ProfileResponse> = _detailUser

    private val _followersUser = MutableLiveData<List<ItemsItem>>()
    val followersUser: LiveData<List<ItemsItem>> = _followersUser

    private val _followingUser = MutableLiveData<List<ItemsItem>>()
    val followingUser: LiveData<List<ItemsItem>> = _followingUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var username: String? = null

    suspend fun getUser() {
        if (!username.isNullOrBlank()) {
            viewModelScope.launch {
                getDetailUser().collect { response ->
                    if (response is ApiResponse.Success) {
                        _detailUser.postValue(response.data!!)
                    }
                }
            }
        }
    }

    suspend fun getFollow() {
        viewModelScope.launch {
            getFollowers().collect { response ->
                if (response is ApiResponse.Success) {
                    _followersUser.postValue(response.data!!)
                }
            }
            getFollowing().collect { response ->
                if (response is ApiResponse.Success) {
                    _followingUser.postValue(response.data!!)
                }
            }
        }
    }


    suspend fun getDetailUser(): Flow<ApiResponse<ProfileResponse>> {
        return flow {
            _isLoading.postValue(true)
            try {
                val response = ApiConfig.getApiService().getProfile(username ?: "")
                if (response.isSuccessful && response.body() != null) {
                    emit(ApiResponse.Success(response.body()!!))
                } else {
                    emit(ApiResponse.Error("Failed to fetch profile"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }finally {
                _isLoading.postValue(false)
            }
        }.flowOn(Dispatchers.IO)
    }

//    suspend fun getDetailUser(username: String?): Flow<ApiService<ProfileResponse>> {
//        return flow {
//            try {
//                val response = ApiConfig.getApiService().getProfile(username ?: "")
//                val dataArray = response.name
//                if (dataArray.isNotEmpty()) {
//                    emit(ApiConfig.getApiService().)
//                } else {
//                    emit(ApiResponse.Error(response.message()))
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//    private suspend fun getDetailUser() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getProfile(username ?: "")
//        client.enqueue(object : Callback<ProfileResponse> {
//            override fun onResponse(
//                call: Call<ProfileResponse>,
//                response: Response<ProfileResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _detailUser.value = response.body()
//                } else {
//                    Log.e("DetailViewModel", "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e("DetailViewModel", "onFailure: ${t.message}")
//            }
//
//        })
//    }


    suspend fun getFollowers(): Flow<ApiResponse<List<ItemsItem>>> {
        return flow {
            _isLoading.postValue(true)
            try {
                val response = ApiConfig.getApiService().getFollowers(username ?: "")
                if (response.isSuccessful) {
                    emit(ApiResponse.Success<List<ItemsItem>>(response.body() ?: emptyList()))
                } else {
                    emit(ApiResponse.Error("Failed to fetch followers"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }finally {
                _isLoading.postValue(false)
            }
        }.flowOn(Dispatchers.IO)
    }


//    private suspend fun getFollowers() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getFollowing(username ?: "")
//        client.enqueue(object : Callback<List<ItemsItem>> {
//            override fun onResponse(
//                call: Call<List<ItemsItem>>,
//                response: Response<List<ItemsItem>>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _followingUser.value = response.body()
//                    Log.d("Followers", "Isi followers ${response.body()}")
//
//                } else {
//                    Log.e(
//                        "DetailViewModel",
//                        "onFailure: ${response.message()} & ${response.body()}"
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
//                Log.e("DetailViewModel", "onFailure: ${t.message}")
//            }
//
//        })
//    }


    suspend fun getFollowing(): Flow<ApiResponse<List<ItemsItem>>> {
        return flow {
            _isLoading.postValue(true)
            try {
                val response = ApiConfig.getApiService().getFollowing(username ?: "")
                if (response.isSuccessful) {
                    emit(ApiResponse.Success<List<ItemsItem>>(response.body() ?: emptyList()))
                } else {
                    emit(ApiResponse.Error("Failed to fetch followers"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }finally {
                _isLoading.postValue(false)
            }
        }.flowOn(Dispatchers.IO)
    }

//    private suspend fun getFollowing() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getFollowing(username ?: "")
//        client.enqueue(object : Callback<List<ItemsItem>> {
//            override fun onResponse(
//                call: Call<List<ItemsItem>>,
//                response: Response<List<ItemsItem>>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _followersUser.value = response.body()
//                    Log.d("Followers", "Isi followers ${response.body()}")
//
//                } else {
//                    Log.e(
//                        "DetailViewModel",
//                        "onFailure: ${response.message()} & ${response.body()}"
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
//                Log.e("DetailViewModel", "onFailure: ${t.message}")
//            }
//
//        })
//    }
}