package com.android.samples.arch.componentsbasicsample.ui.homepage

class HomePresenterImpl : HomePresenter {
    private lateinit var homepageView: HomeView
    override fun setView(homeView: HomeView) {
        this.homepageView = homeView
    }

    override fun loadHomePage() {
        this.homepageView.displayHomePage("来自Presenter的result")
    }
}
