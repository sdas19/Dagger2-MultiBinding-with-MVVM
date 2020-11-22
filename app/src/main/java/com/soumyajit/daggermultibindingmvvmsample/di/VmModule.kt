package com.soumyajit.daggermultibindingmvvmsample.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.Cache
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class VmModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

}
