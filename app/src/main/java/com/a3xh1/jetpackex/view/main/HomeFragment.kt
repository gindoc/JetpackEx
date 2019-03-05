package com.a3xh1.jetpackex.view.main

import android.widget.Toast
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentHomeBinding
import javax.inject.Inject
import com.a3xh1.jetpackex.R
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
class HomeFragment @Inject constructor(): BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home

    @Inject
    @field:Named("home")
    lateinit var className:String

    override fun initView() {
        super.initView()
        Toast.makeText(context, className, Toast.LENGTH_SHORT).show()
    }


}