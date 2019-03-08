package com.a3xh1.jetpackex.di.modules.repo

import com.a3xh1.jetpackex.data.remote.LoginService
import com.a3xh1.jetpackex.di.modules.ServiceModules
import com.a3xh1.jetpackex.view.auth.login.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */

@Module
class LoginRepoModule{

    @Provides
    fun getLoginDataResourceRepository(
        remote: ILoginRemoteDataSource,
        local: ILoginLocalDataSource
    ): LoginDataResourceRepository {
        return LoginDataResourceRepository(remote, local)
    }

    @Provides
    fun getLoginRemoteDataSource(@Named("loginService") service:LoginService): ILoginRemoteDataSource {
        return LoginRemoteDataRepository(service)
    }

    @Provides
    fun getLoginLocalDataSource(): ILoginLocalDataSource {
        return LoginLocalDataSource()
    }
}