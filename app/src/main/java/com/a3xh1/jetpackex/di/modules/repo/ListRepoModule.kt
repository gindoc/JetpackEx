package com.a3xh1.jetpackex.di.modules.repo

import com.a3xh1.jetpackex.data.remote.ListService
import com.a3xh1.jetpackex.view.list.IListRemoteDataSource
import com.a3xh1.jetpackex.view.list.ListRemoteDataSource
import com.a3xh1.jetpackex.view.list.ListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/9.
 * FOR   :
 */
@Module
class ListRepoModule {

    @Provides
    fun provideListRepository(remote: IListRemoteDataSource): ListRepository {
        return ListRepository(remote)
    }

    @Provides
    fun provideListRemoteRepository(@Named("listService") service: ListService): IListRemoteDataSource {
        return ListRemoteDataSource(service)
    }

}