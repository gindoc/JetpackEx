package com.a3xh1.jetpackex.base.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
abstract class BaseFragment<B : ViewDataBinding> : InjectFragment(){

    private var mRootView: View? = null

    protected lateinit var binding: B

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mRootView = LayoutInflater.from(context).inflate(layoutId, container, false)
        return mRootView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding(view)
        initView()
    }

    fun initBinding(rootView: View) {
        binding = DataBindingUtil.bind(rootView)!!
        with(binding) {
            lifecycleOwner = this@BaseFragment
        }
    }

    open fun initView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
        binding.unbind()
    }
}