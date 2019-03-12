package com.a3xh1.jetpackex.view.list

import com.a3xh1.basecore.base.repository.BaseRepositoryRemote
import com.a3xh1.basecore.base.repository.IRemoteDataSource
import com.a3xh1.basecore.pojo.Response
import com.a3xh1.jetpackex.data.remote.ListService
import com.a3xh1.jetpackex.pojo.Medicine
import io.reactivex.Flowable

/**
 * Author: GIndoc on 2019/3/9.
 * FOR   :
 */

class ListRepository(remoteDataSource: IListRemoteDataSource):BaseRepositoryRemote<IListRemoteDataSource>(remoteDataSource){
    fun requestList(page: Int): Flowable<Response<List<Medicine>>> {
        return remoteDataSource.requestList(page)
    }

}


interface IListRemoteDataSource:IRemoteDataSource {
    fun requestList(page: Int): Flowable<Response<List<Medicine>>>
}

class ListRemoteDataSource(val service: ListService):IListRemoteDataSource {
    override fun requestList(page: Int): Flowable<Response<List<Medicine>>> {
        return service.requestList(page)
    }
}