package com.android.samples.arch.componentsbasicsample.ui.homepage

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.design.widget.Snackbar.make
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.samples.arch.componentsbasicsample.R
import com.android.samples.arch.componentsbasicsample.application.TestApplication
import com.android.samples.arch.componentsbasicsample.ui.detail.DetailActivity

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity() : AppCompatActivity(), HomeView {
    @Inject
    lateinit var presenter: HomePresenter


//    val faBtn: Button by bindView(R.id.btnT)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //绑定初始化ButterKnife
//        ButterKnife.bind(this);
        setSupportActionBar(toolbar)

        (application as TestApplication).testAppComponent.inject(this)
        presenter.setView(this)

        fab.setOnClickListener {
//            view ->
//            make(view, "Replace with your own action", LENGTH_LONG)
//                    .setAction("Action", null).show()
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
        }
    }

//    @OnClick(R.id.btnT)
//    fun onClickBtn(){
//        print("onCLickBtn!!!!")
//        presenter.loadHomePage()
//    }
    override fun displayHomePage(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }


}
