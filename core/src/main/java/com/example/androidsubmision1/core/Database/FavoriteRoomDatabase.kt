package com.example.androidsubmission1.core.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidsubmission1.core.domain.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDatabase::class.java, "favoriteUser"
                    )
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }
}