package com.soumyajit.daggermultibindingmvvmsample

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

fun ViewModel.addToDisposable(d : Disposable){
    DisposableManager.add(d)
}

fun ViewModel.removeAllDisposables(){
    DisposableManager.dispose()
}