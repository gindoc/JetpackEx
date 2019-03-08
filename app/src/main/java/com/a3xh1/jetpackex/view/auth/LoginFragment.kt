package com.a3xh1.jetpackex.view.auth

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentLoginBinding
import com.a3xh1.jetpackex.R
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import java.lang.Exception
import java.util.concurrent.TimeUnit

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

        viewModel.user
            .observe(this, Observer {
                Toast.makeText(context, it?.nickname, Toast.LENGTH_SHORT).show()
            })

        viewModel.error
            .observe(this, Observer {
                Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
            })

    }

    fun toRegisterPage(view: View) =
        Navigation.findNavController(view).navigate(R.id.action_register)

    fun login() = viewModel.login()

}