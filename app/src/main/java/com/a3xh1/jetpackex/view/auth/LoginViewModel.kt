package com.a3xh1.jetpackex.view.auth

import com.a3xh1.base.viewmodel.BaseViewModel
import com.a3xh1.jetpackex.view.auth.login.LoginDataResourceRepository
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
class LoginViewModel @Inject constructor(val repo: LoginDataResourceRepository) : BaseViewModel() {

    val test = "111"

    fun login() = repo.login()
}