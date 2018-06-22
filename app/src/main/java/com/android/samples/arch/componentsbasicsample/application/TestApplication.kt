package com.android.samples.arch.componentsbasicsample.application

import android.app.Application
import com.android.samples.arch.componentsbasicsample.dagger.DaggerTestAppComponent
import com.android.samples.arch.componentsbasicsample.dagger.TestAppComponent
import com.android.samples.arch.componentsbasicsample.dagger.TestAppModule

class TestApplication : Application() {

    lateinit var testAppComponent: TestAppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }

    fun initDagger(app: TestApplication){
        testAppComponent =  DaggerTestAppComponent.builder()
                .testAppModule(TestAppModule(app))
                .build()
    }


}