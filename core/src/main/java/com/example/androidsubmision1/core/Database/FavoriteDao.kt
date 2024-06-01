package com.example.androidsubmission1.core.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidsubmission1.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getUsername(username: String): Flow<Favorite>

    @Query("SELECT * FROM favorite where isFavorite = 1")
    fun getFavorited(): Flow<List<Favorite>>

}