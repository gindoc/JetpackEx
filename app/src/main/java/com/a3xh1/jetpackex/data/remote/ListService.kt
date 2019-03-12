package com.a3xh1.jetpackex.data.remote

import com.a3xh1.basecore.pojo.Response
import com.a3xh1.jetpackex.pojo.Medicine
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: GIndoc on 2019/3/9.
 * FOR   :
 */
interface ListService {

    @GET("Drug/index")
    fun requestList(@Query("page") page: Int): Flowable<Response<List<Medicine>>>

}