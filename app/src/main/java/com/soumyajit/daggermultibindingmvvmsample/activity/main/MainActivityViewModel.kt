package com.soumyajit.daggermultibindingmvvmsample.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soumyajit.daggermultibindingmvvmsample.*
import com.soumyajit.daggermultibindingmvvmsample.db.UsersDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class MainActivityViewModel
    @Inject constructor(private val apiClient: ApiClient,
                        private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
                        private val usersDao: UsersDao)
    : ViewModel() {
    private val _state : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state

    init {
        _state.postValue(MainActivityViewState.ShowLoading)
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            withContext(coroutinesDispatcherProvider.io){
                flowOf(apiClient.getData())
                    .catch { throwable ->
                        _state.postValue(MainActivityViewState.ShowError(
                            throwable)
                        )
                    }.map { result ->
                        if(!result.data.isNullOrEmpty()){
                            usersDao.deleteAllUsers()
                            usersDao.insertUsers(result.data)
                        }
                    }.collect()
            }
        }
    }
}