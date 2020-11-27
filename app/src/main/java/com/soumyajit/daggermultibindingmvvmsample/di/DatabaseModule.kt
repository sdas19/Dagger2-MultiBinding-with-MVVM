package com.soumyajit.daggermultibindingmvvmsample.di

import android.content.Context
import androidx.room.Room
import com.soumyajit.daggermultibindingmvvmsample.Constants.STARWARS_DB_NAME
import com.soumyajit.daggermultibindingmvvmsample.database.StarWarsDB
import com.soumyajit.daggermultibindingmvvmsample.database.StarWarsPeopleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    private val dBName = STARWARS_DB_NAME

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): StarWarsDB {
        return Room.databaseBuilder(
            appContext,
            StarWarsDB::class.java,
            dBName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePeopleDao(db: StarWarsDB): StarWarsPeopleDao {
        return db.getPeopleDao()
    }
}