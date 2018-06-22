package com.android.samples.arch.componentsbasicsample.dagger

import com.android.samples.arch.componentsbasicsample.ui.homepage.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestAppModule::class,
    PresentModule::class
])
interface TestAppComponent {
    fun inject(target: HomeActivity)
}