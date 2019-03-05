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
class HomeFragment /*@Inject constructor()*/: BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home

    // 如果需要注入其他数据，则应该在MainActivityModule中provideHomeFragment，否则用@inject constructor的方式编译通不过
    @field:Named("homeString")
    @Inject
    lateinit var className:String

    @Inject
    @JvmField
    var num:Int=0


    override fun initView() {
        super.initView()
        Toast.makeText(context, "$className  $num", Toast.LENGTH_SHORT).show()
    }


}