package com.a3xh1.jetpackex.data.remote

import com.a3xh1.jetpackex.pojo.User
import com.a3xh1.pojo.Response
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */

interface LoginService{


    @POST("Login/login")
    fun login(@Query("tel") phone: String, @Query("pass") pwd: String): Flowable<Response<User>>
}