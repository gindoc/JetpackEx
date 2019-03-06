package com.a3xh1.jetpackex.view.auth

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentLoginBinding
import com.a3xh1.jetpackex.R
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int = R.layout.fragment_login

//    lateinit var viewModel: LoginViewModel
    private val viewModel by lazy { getInjectViewModel(LoginViewModel::class.java) }

    override fun initView() {
        super.initView()
        binding.view = this

//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    }


    fun toRegisterPage(view: View) =
        Navigation.findNavController(view).navigate(R.id.action_register)


    fun login() = Toast.makeText(context, viewModel.test, Toast.LENGTH_SHORT).show()

}