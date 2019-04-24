package com.a3xh1.jetpackex.view.main

import android.content.Intent
import android.view.View
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentChildBinding
import com.a3xh1.jetpackex.R
import com.a3xh1.jetpackex.view.flutter.FlutterActivity
import kotlinx.android.synthetic.main.fragment_child.*
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
class ChildFragment @Inject constructor() : BaseFragment<FragmentChildBinding>() {

    override val layoutId: Int = R.layout.fragment_child

    override fun initView() =
        text.setOnClickListener {
            startActivity(Intent(context, FlutterActivity::class.java))
        }

}