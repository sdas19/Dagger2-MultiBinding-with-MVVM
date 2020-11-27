package com.soumyajit.daggermultibindingmvvmsample.di

import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiDbUseCase
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiDbUseCaseImpl
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiRemoteUseCase
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiRemoteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindSwapiRemoteUseCase(swapiRemoteUseCaseImpl: SwapiRemoteUseCaseImpl) : SwapiRemoteUseCase

    @Binds
    abstract fun bindSwapiDBUseCase(swapiDBUseCaseImpl: SwapiDbUseCaseImpl) : SwapiDbUseCase
}