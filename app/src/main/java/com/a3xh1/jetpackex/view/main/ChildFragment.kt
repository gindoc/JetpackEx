package com.a3xh1.jetpackex.view.main

import com.a3xh1.jetpackex.R
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentChildBinding
import io.flutter.embedding.android.FlutterActivity
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
            context?.let { it1 ->
                val intent = FlutterActivity.withNewEngine()
                    .initialRoute("/")
                    .build(it1)
                startActivity(intent)
            }
        }

}