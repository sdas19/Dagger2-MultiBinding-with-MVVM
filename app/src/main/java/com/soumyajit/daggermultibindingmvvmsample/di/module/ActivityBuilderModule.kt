package com.soumyajit.daggermultibindingmvvmsample.di.module

import com.soumyajit.daggermultibindingmvvmsample.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): MainActivity

}