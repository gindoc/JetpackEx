package com.a3xh1.jetpackex.view.auth

import androidx.lifecycle.MutableLiveData
import com.a3xh1.base.viewmodel.BaseViewModel
import com.a3xh1.common.rx.handleResponse
import com.a3xh1.ext.reactivex.subscribes
import com.a3xh1.jetpackex.base.state.SimpleViewState
import com.a3xh1.jetpackex.pojo.User
import com.a3xh1.jetpackex.view.auth.login.LoginDataResourceRepository
import com.a3xh1.utils.logger.loge
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
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
        .map {
            SimpleViewState.result(it)
        }
        .startWith(SimpleViewState.loading())
        .onErrorReturn { SimpleViewState.error(it) }
        .autoDisposable(this)
        .subscribes {
            when (it) {
                is SimpleViewState.Refreshing -> error.value = Exception("网络请求中")
//                is SimpleViewState.Error -> error.value = it.error
                is SimpleViewState.Result -> user.value = it.result.data
            }
        }
//        .subscribes {
//            user.postValue(it.data)
//        }
}
