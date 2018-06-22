package com.android.samples.arch.componentsbasicsample.ui.homepage

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.samples.arch.componentsbasicsample.R
import com.android.samples.arch.componentsbasicsample.application.TestApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity() : AppCompatActivity(), HomeView {
    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        (application as TestApplication).testAppComponent.inject(this)
        presenter.setView(this)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            presenter.loadHomePage()
        }
    }

    override fun displayHomePage(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }

}
