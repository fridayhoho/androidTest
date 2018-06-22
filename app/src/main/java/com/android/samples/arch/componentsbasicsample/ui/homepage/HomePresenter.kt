package com.android.samples.arch.componentsbasicsample.ui.homepage

interface HomePresenter {
    fun setView(homeView: HomeView)

    fun loadHomePage()
}