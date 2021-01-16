package com.soumyajit.daggermultibindingmvvmsample.di.module

import com.soumyajit.daggermultibindingmvvmsample.activity.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.InternalCoroutinesApi

@Module
abstract class ActivityBuilderModule {

    @InternalCoroutinesApi
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): MainActivity

}