package com.soumyajit.daggermultibindingmvvmsample.activity.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soumyajit.daggermultibindingmvvmsample.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val apiClient: ApiClient,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _state: MutableStateFlow<MainActivityViewState> = MutableStateFlow(MainActivityViewState.ShowLoading)
    val state: StateFlow<MainActivityViewState> = _state

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            when(val response = runIO(coroutinesDispatcherProvider){apiClient.getData()}){
                is ResultHandler.Error -> {
                    _state.value = MainActivityViewState.ShowError(response.throwable)
                }
                is ResultHandler.Success -> {
                    val result = response.data
                    Log.i(TAG,result.toString())
                    _state.value = MainActivityViewState.ShowData(result.data)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

    companion object {
        private val TAG = MainActivityViewModel::class.java.simpleName
    }
}