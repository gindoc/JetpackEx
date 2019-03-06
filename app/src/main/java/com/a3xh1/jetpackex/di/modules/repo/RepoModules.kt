package com.a3xh1.jetpackex.di.modules.repo

import com.a3xh1.jetpackex.view.auth.login.*
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
@Module(includes = [LoginRepoModule::class])
class RepoModules {

//    @Provides
//    fun getLoginDataResourceRepository(
//        remote: ILoginRemoteDataSource,
//        local: ILoginLocalDataSource
//    ): LoginDataResourceRepository {
//        return LoginDataResourceRepository(remote, local)
//    }
//
//    @Provides
//    fun getLoginRemoteDataSource(): ILoginRemoteDataSource {
//        return LoginRemoteDataRepository()
//    }
//
//    @Provides
//    fun getLoginLocalDataSource(): ILoginLocalDataSource {
//        return LoginLocalDataSource()
//    }
}