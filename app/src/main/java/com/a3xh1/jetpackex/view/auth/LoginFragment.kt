package com.a3xh1.jetpackex.view.auth

import android.view.View
import android.widget.Toast
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

    private val viewModel by lazy { getInjectViewModel(LoginViewModel::class.java) }

    override fun initView() {
        super.initView()
        binding.view = this

    }


    fun toRegisterPage(view: View) =
        Navigation.findNavController(view).navigate(R.id.action_register)


    fun login() = viewModel.login().let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

}