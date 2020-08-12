package com.example.architecturecomponents.model.service.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.architecturecomponents.model.bo.User

// TODO Implement Room
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getFavoriteUsers(): LiveData<List<User>>

    @Insert
    suspend fun insertFavoriteUser(user: User)

    @Delete
    suspend fun deleteFavoriteUser(id: String)
}