package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.data.remote.ListService
import com.a3xh1.jetpackex.data.remote.LoginService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */
@Module
class ServiceModules {

    @Provides
    @Named("loginService")
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Named("listService")
    fun provideListService(retrofit: Retrofit):ListService{
        return retrofit.create(ListService::class.java)
    }
}