package com.a3xh1.jetpackex.view.auth.login

import com.a3xh1.basecore.base.repository.BaseRepositoryBoth
import com.a3xh1.basecore.base.repository.ILocalDataSource
import com.a3xh1.basecore.base.repository.IRemoteDataSource
import com.a3xh1.jetpackex.data.remote.LoginService
import com.a3xh1.jetpackex.pojo.User
import com.a3xh1.basecore.pojo.Response
import io.reactivex.Flowable

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
    fun login(phone: String, password: String): Flowable<Response<User>>
}

interface ILoginLocalDataSource : ILocalDataSource

class LoginDataResourceRepository constructor(
    remoteDataSource: ILoginRemoteDataSource,
    localDataSource: ILoginLocalDataSource
) : BaseRepositoryBoth<ILoginRemoteDataSource, ILoginLocalDataSource>(remoteDataSource, localDataSource) {

    fun login(phone: String, password: String) = remoteDataSource.login(phone, password)
}

class LoginRemoteDataRepository(val loginService: LoginService) : ILoginRemoteDataSource {

    override fun login(phone: String, password: String): Flowable<Response<User>> {
        return loginService.login(phone, password)
    }
}

class LoginLocalDataSource : ILoginLocalDataSource