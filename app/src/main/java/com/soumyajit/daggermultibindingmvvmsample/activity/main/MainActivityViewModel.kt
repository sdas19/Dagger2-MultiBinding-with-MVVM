package com.soumyajit.daggermultibindingmvvmsample.activity.main

import android.content.Context
import android.util.Log
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

class MainActivityViewModel
    @Inject constructor(private val apiClient: ApiClient,
                        private val usersDao: UsersDao)
    : ViewModel() {
    val TAG = MainActivityViewModel::class.java.simpleName
    private val _state : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state

    init {
        _state.postValue(MainActivityViewState.ShowLoading)
        getData()
    }

    @ExperimentalCoroutinesApi
    private fun getData() {
        viewModelScope.launch {
            flowOf(apiClient.getData())
                .catch { throwable -> _state.postValue(
                    MainActivityViewState.ShowError(throwable)) }
                .map { result ->
                    if(!result.data.isNullOrEmpty()){
                        usersDao.insertUsers(result.data)
                    }
                }.collect()
        }
    }
}