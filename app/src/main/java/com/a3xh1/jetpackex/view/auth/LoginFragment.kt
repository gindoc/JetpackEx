package com.a3xh1.jetpackex.view.auth

import android.view.View
import androidx.navigation.Navigation
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentLoginBinding
import com.a3xh1.jetpackex.R

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutId: Int = R.layout.fragment_login

    override fun initView() {
        super.initView()
        binding.view = this
    }


    fun toRegisterPage(view: View) =
        Navigation.findNavController(view).navigate(R.id.action_register)


    fun login() =

}