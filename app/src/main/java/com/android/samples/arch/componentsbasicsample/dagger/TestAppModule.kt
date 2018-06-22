package com.android.samples.arch.componentsbasicsample.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}