package com.soumyajit.daggermultibindingmvvmsample.di

import com.soumyajit.daggermultibindingmvvmsample.repository.StarWarsPeopleRepository
import com.soumyajit.daggermultibindingmvvmsample.repository.StarWarsPeopleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class VmModule {

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun bindStarWarsPeopleRepository(
        starWarsPeopleRepositoryImpl: StarWarsPeopleRepositoryImpl
    ): StarWarsPeopleRepository
}