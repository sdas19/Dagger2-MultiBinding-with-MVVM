package com.soumyajit.daggermultibindingmvvmsample.activity

import com.soumyajit.daggermultibindingmvvmsample.SingleDataModel

sealed class MainActivityViewState {
    object ShowLoading : MainActivityViewState()
    class ShowError(val error: Throwable) : MainActivityViewState()
    class ShowData(val data: List<SingleDataModel>) : MainActivityViewState()
}