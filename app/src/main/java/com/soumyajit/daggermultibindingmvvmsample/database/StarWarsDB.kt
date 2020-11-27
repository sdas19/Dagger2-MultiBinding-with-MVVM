package com.soumyajit.daggermultibindingmvvmsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

@Database(entities = [(StarWarsPeople::class)], version = 1)
abstract class StarWarsDB: RoomDatabase() {
    abstract fun getPeopleDao() : StarWarsPeopleDao
}