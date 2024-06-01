package com.example.androidsubmision1.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsubmision1.ApiConfig
import com.example.androidsubmision1.GithubResponse
import com.example.androidsubmision1.ItemsItem
import com.example.androidsubmission1.core.API.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val _userList = MutableLiveData<List<ItemsItem>>()
    val userList: LiveData<List<ItemsItem>> = _userList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var searchUser: String? = null

    companion object {
        private const val TAG = "MainViewModel"
        private const val USER_Q = "a"
    }

    init {

        viewModelScope.launch {
            getUserList().collect { response ->
                if (response is ApiResponse.Success) {
                    _userList.postValue(response.data.items)
                }
            }
        }
        //getUserList()
    }

    fun refresh() {
        viewModelScope.launch {
            getUserList().collect { response ->
                if (response is ApiResponse.Success) {
                    _userList.postValue(response.data.items)
                }
            }
        }
        //getUserList()
    }

    suspend fun getUserList(): Flow<ApiResponse<GithubResponse>> {
        return flow {
            _isLoading.postValue(true)
            try {
                val response = ApiConfig.getApiService().getUsers(searchUser ?: USER_Q)
                if (response.isSuccessful && response.body() != null) {
                    emit(ApiResponse.Success(response.body()!!))
                    Log.d(TAG, "tes sukses: ${response.message()}")
                } else {
                    emit(ApiResponse.Error("onFailureResponse: ${response.message()}"))
                    Log.e(TAG, "onFailureResponse: ${response.message()}")

                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            } finally {
                _isLoading.postValue(false)
            }
        }.flowOn(Dispatchers.IO)
    }

//    private fun getUserList() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getUsers(searchUser ?: USER_Q)
//        client.enqueue(object : Callback<GithubResponse> {
//            override fun onResponse(
//                call: Call<GithubResponse>,
//                response: Response<GithubResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _userList.value = response.body()?.items
//                } else {
//                    Log.e(TAG, "onFailureResponse: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//
//        })
//    }
}
