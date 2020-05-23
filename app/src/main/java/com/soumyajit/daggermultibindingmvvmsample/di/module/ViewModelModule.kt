package com.soumyajit.daggermultibindingmvvmsample.di.module

import androidx.lifecycle.ViewModel
import com.soumyajit.daggermultibindingmvvmsample.activity.main.MainActivityViewModel
import com.soumyajit.daggermultibindingmvvmsample.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel) : ViewModel
}