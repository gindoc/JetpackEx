package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.di.modules.repo.LoginRepoModule
import dagger.Module

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