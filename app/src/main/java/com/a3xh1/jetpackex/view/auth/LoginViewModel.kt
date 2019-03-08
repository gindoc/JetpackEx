package com.a3xh1.jetpackex.view.auth

import androidx.lifecycle.MutableLiveData
import com.a3xh1.base.viewmodel.BaseViewModel
import com.a3xh1.common.rx.handleResponse
import com.a3xh1.ext.reactivex.subscribes
import com.a3xh1.jetpackex.pojo.User
import com.a3xh1.jetpackex.view.auth.login.LoginDataResourceRepository
import com.a3xh1.utils.logger.loge
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
class LoginViewModel @Inject constructor(val repo: LoginDataResourceRepository) : BaseViewModel() {

    val user = MutableLiveData<User>()

    val error = MutableLiveData<Throwable>()

    init {
        RxJavaPlugins.setErrorHandler {
            loge { it.message ?: "" }
        }
    }

    fun login() = repo.login("17322100985", "123456")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(handleResponse())
        .autoDisposable(this)
        .subscribes {
            user.postValue(it.data)
        }
}
