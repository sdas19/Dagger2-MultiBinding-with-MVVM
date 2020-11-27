package com.soumyajit.daggermultibindingmvvmsample.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.soumyajit.daggermultibindingmvvmsample.Constants.PAGE_SIZE
import com.soumyajit.daggermultibindingmvvmsample.CoroutinesDispatcherProvider
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiDbUseCase
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiRemoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class StarWarsPeopleRepositoryImpl @Inject constructor(
    private val swapiRemoteUseCase: SwapiRemoteUseCase,
    private val swapiDbUseCase: SwapiDbUseCase,
    private val coroutineDispatcherProvider: CoroutinesDispatcherProvider
): StarWarsPeopleRepository {
    private val boundaryCallback = StarWarsPeopleBoundaryCallback(
        swapiRemoteUseCase = swapiRemoteUseCase,
        swapiDbUseCase = swapiDbUseCase,
        coroutineDispatcherProvider = coroutineDispatcherProvider
    )

    override fun fetchOrGetPeople(): LiveData<PagedList<StarWarsPeople>> = LivePagedListBuilder(swapiDbUseCase.getPeople(), PAGE_SIZE)
        .setBoundaryCallback(boundaryCallback)
        .build()

    override fun invalidate() = boundaryCallback.invalidate()
}