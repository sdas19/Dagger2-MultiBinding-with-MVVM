package com.soumyajit.daggermultibindingmvvmsample.activity.main

sealed class MainActivityViewState {
    object ShowLoading : MainActivityViewState()
    class ShowError(val error: Throwable) : MainActivityViewState()
}