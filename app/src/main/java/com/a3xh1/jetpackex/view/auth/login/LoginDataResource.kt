package com.a3xh1.jetpackex.view.auth.login

import com.a3xh1.base.repository.BaseRepositoryBoth
import com.a3xh1.base.repository.ILocalDataSource
import com.a3xh1.base.repository.IRemoteDataSource
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
//interface ILoginRemoteDataSource : IRemoteDataSource{
//    fun login():String
//}
//
//interface ILoginLocalDataSource : ILocalDataSource
//
//class LoginDataResourceRepository @Inject constructor(
//    remoteDataSource: LoginRemoteDataRepository,
//    localDataSource: LoginLocalDataSource
//) : BaseRepositoryBoth<ILoginRemoteDataSource, ILoginLocalDataSource>(remoteDataSource, localDataSource){
//
//    fun login() = remoteDataSource.login()
//}
//
//class LoginRemoteDataRepository @Inject constructor(): ILoginRemoteDataSource {
//
//    override fun login():String{
//        return "登录成功"
//    }
//}
//
//class LoginLocalDataSource @Inject constructor(): ILoginLocalDataSource

interface ILoginRemoteDataSource : IRemoteDataSource {
    fun login(): String
}

interface ILoginLocalDataSource : ILocalDataSource

class LoginDataResourceRepository constructor(
    remoteDataSource: ILoginRemoteDataSource,
    localDataSource: ILoginLocalDataSource
) : BaseRepositoryBoth<ILoginRemoteDataSource, ILoginLocalDataSource>(remoteDataSource, localDataSource) {

    fun login() = remoteDataSource.login()
}

class LoginRemoteDataRepository : ILoginRemoteDataSource {

    override fun login(): String {
        return "登录成功"
    }
}

class LoginLocalDataSource : ILoginLocalDataSource