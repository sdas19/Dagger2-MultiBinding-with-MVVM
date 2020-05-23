package com.soumyajit.daggermultibindingmvvmsample

import kotlinx.coroutines.withContext

suspend fun <T : Any> runIO(dispatcherProvider: CoroutinesDispatcherProvider, work: suspend () -> T): ResultHandler<T> = withContext(dispatcherProvider.io) {
    try {
        val result = work()
        withContext(dispatcherProvider.main) {
            when (result) {
                is Throwable -> ResultHandler.Error(result)
                else -> ResultHandler.Success(result)
            }
        }
    } catch (ex: Exception) {
        withContext(dispatcherProvider.main) {
            ResultHandler.Error(ex)
        }
    }
}

sealed class ResultHandler<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultHandler<T>()
    data class Error(val throwable: Throwable) : ResultHandler<Nothing>()
}