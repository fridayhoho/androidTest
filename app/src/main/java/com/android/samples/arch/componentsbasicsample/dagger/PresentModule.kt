package com.android.samples.arch.componentsbasicsample.dagger

import com.android.samples.arch.componentsbasicsample.ui.homepage.HomePresenter
import com.android.samples.arch.componentsbasicsample.ui.homepage.HomePresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentModule {
    @Provides
    @Singleton
    fun  provideHomepagePresenter():HomePresenter = HomePresenterImpl()
}