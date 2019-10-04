package com.soumyajit.daggermultibindingmvvmsample.activity

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soumyajit.daggermultibindingmvvmsample.ApiClient
import com.soumyajit.daggermultibindingmvvmsample.DataModel
import com.soumyajit.daggermultibindingmvvmsample.DisposableManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivityViewModel
    @Inject constructor(private val context : Context, private val apiClient: ApiClient): ViewModel() {
    val TAG = MainActivityViewModel::class.java.simpleName
    val dataResponse : MutableLiveData<DataModel> = MutableLiveData()

    init {
        DisposableManager.add(
            apiClient.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response -> Log.i(TAG,response.toString())
                        dataResponse.value = response},
                    {error -> Log.i(TAG,error.toString())}
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        DisposableManager.dispose()
    }
}