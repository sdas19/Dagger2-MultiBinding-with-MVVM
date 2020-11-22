package com.soumyajit.daggermultibindingmvvmsample.activity.main

import com.soumyajit.daggermultibindingmvvmsample.*
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
): UseCase {

    override suspend fun getData(): ResultHandler<DataModel> {
        return runIO(coroutinesDispatcherProvider){apiClient.getData()}
    }
}

interface UseCase {
    suspend fun getData() : ResultHandler<DataModel>
}