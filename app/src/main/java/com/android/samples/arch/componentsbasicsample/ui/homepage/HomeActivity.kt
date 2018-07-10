package com.android.samples.arch.componentsbasicsample.ui.homepage


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.samples.arch.componentsbasicsample.R
import com.android.samples.arch.componentsbasicsample.application.TestApplication
import com.android.samples.arch.componentsbasicsample.ui.news.BlankFragment
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
//        setSupportActionBar(toolbar)

        (application as TestApplication).testAppComponent.inject(this)
        presenter.setView(this)
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        fab.setOnClickListener {
////            view ->
////            make(view, "Replace with your own action", LENGTH_LONG)
////                    .setAction("Action", null).show()
//            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this@HomeActivity, DemoLoadMoreActivity::class.java))
//        }
//        btnT.setOnClickListener{
//            startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
//        }
    }

    override fun displayHomePage(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {
                val songsFragment = BlankFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
