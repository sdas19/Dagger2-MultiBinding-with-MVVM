package com.soumyajit.daggermultibindingmvvmsample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soumyajit.daggermultibindingmvvmsample.Users
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class UsersDao {

    @Query("SELECT * FROM Users")
    abstract fun getAllUsers(): Flow<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(users : List<Users>)

    @Query("DELETE FROM Users")
    abstract fun deleteAllUsers()

    @Query("SELECT * FROM Users WHERE id = :id")
    abstract fun getUser(id: String): Flow<Users>

    @ExperimentalCoroutinesApi
    fun getUserDistinctUntilChanged(id:String) =
        getUser(id).distinctUntilChanged()
  
}