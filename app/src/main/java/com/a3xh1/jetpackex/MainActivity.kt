package com.a3xh1.jetpackex

import android.os.Bundle
import android.widget.Toast
import com.a3xh1.jetpackex.base.view.activity.BaseActivity
import com.a3xh1.jetpackex.databinding.ActivityMainBinding
import com.a3xh1.jetpackex.view.main.HomeFragment
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    @Inject
    lateinit var className:String

    @Inject
    lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, className, Toast.LENGTH_SHORT).show()

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.cl_container, homeFragment)
        beginTransaction.commit()

    }
}
