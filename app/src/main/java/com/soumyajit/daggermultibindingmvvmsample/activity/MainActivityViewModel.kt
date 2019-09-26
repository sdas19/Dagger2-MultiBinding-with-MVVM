package com.soumyajit.daggermultibindingmvvmsample.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivityViewModel
    @Inject constructor(private val context : Context, private val retrofit: Retrofit): ViewModel() {

}