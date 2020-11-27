package com.soumyajit.daggermultibindingmvvmsample.repository

import android.util.Log
import androidx.paging.PagedList
import com.soumyajit.daggermultibindingmvvmsample.Constants.GENERIC_ERROR
import com.soumyajit.daggermultibindingmvvmsample.CoroutinesDispatcherProvider
import com.soumyajit.daggermultibindingmvvmsample.ResultHandler
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import com.soumyajit.daggermultibindingmvvmsample.runIO
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiDbUseCase
import com.soumyajit.daggermultibindingmvvmsample.useCase.SwapiRemoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

@ExperimentalCoroutinesApi
class StarWarsPeopleBoundaryCallback(
    private val swapiRemoteUseCase: SwapiRemoteUseCase,
    private val swapiDbUseCase: SwapiDbUseCase,
    private val coroutineDispatcherProvider: CoroutinesDispatcherProvider
): PagedList.BoundaryCallback<StarWarsPeople>() {

    private var requestedPage = 1
    private var isRequestRunning = AtomicBoolean(false)
    private val TAG = StarWarsPeopleBoundaryCallback::class.java.simpleName
    private val ioCoroutineScope by lazy {
        CoroutineScope(coroutineDispatcherProvider.io)
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        fetchAndStorePeople()
    }

    override fun onItemAtEndLoaded(itemAtEnd: StarWarsPeople) {
        super.onItemAtEndLoaded(itemAtEnd)
        fetchAndStorePeople()
    }

    private fun fetchAndStorePeople() {
        if(isRequestRunning.get()){
            return
        }
        ioCoroutineScope.launch {
            isRequestRunning.set(true)
            val response = runIO(coroutineDispatcherProvider) {
                swapiRemoteUseCase.getPeople(requestedPage)
            }
            when(response) {
                is ResultHandler.Success -> {
                    if(response.data.results.isNotEmpty()) {
                        swapiDbUseCase.insertPeople(peopleList = response.data.results)
                    }
                    requestedPage++
                    isRequestRunning.set(false)
                }
                is ResultHandler.Error -> {
                    Log.i(TAG, response.throwable.message ?: GENERIC_ERROR)
                    isRequestRunning.set(false)
                }
            }
        }
    }

    fun invalidate() {
        ioCoroutineScope.cancel()
    }
}