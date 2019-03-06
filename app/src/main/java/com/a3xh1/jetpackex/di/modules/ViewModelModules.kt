package com.a3xh1.jetpackex.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a3xh1.jetpackex.di.modules.viewmodel.ViewModelFactory
import com.a3xh1.jetpackex.di.modules.viewmodel.ViewModelKey
import com.a3xh1.jetpackex.view.auth.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
@Module
abstract class ViewModelModules {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}