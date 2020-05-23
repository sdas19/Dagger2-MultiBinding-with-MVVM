package com.soumyajit.daggermultibindingmvvmsample.activity

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soumyajit.daggermultibindingmvvmsample.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivityViewModel
    @Inject constructor(private val context : Context, private val apiClient: ApiClient): ViewModel() {
    val TAG = MainActivityViewModel::class.java.simpleName
    private val _state : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state

    init {
        _state.postValue(MainActivityViewState.ShowLoading)
        getData()
    }

    private fun getData() {
        addToDisposable(
            apiClient.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response ->
                        Log.i(TAG,response.toString())
                        _state.postValue(MainActivityViewState.ShowData(response.data))
                    },
                    {error ->
                        Log.i(TAG,error.toString())
                        _state.postValue(MainActivityViewState.ShowError(error))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        removeAllDisposables()
    }
}