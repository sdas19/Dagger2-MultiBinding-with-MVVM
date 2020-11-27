package com.soumyajit.daggermultibindingmvvmsample.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

@Dao
interface StarWarsPeopleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(peopleList: List<StarWarsPeople>)

    @Query("SELECT * FROM `STARWARS PEOPLE`")
    fun allPeople(): DataSource.Factory<Int, StarWarsPeople>
}