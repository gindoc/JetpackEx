package com.a3xh1.jetpackex.base.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
abstract class BaseActivity<B : ViewDataBinding> : InjectionActivity() {

    protected lateinit var binding: B

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    fun initBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        with(binding) {
            lifecycleOwner = this@BaseActivity
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}