package com.a3xh1.basecore.base.repository

/**
 * 装饰者模式
 */
open class BaseRepositoryBoth<T : IRemoteDataSource, R : ILocalDataSource>(
        val remoteDataSource: T,
        val localDataSource: R
) : IRepository

open class BaseRepositoryLocal<T : ILocalDataSource>(
        val remoteDataSource: T
) : IRepository

open class BaseRepositoryRemote<T : IRemoteDataSource>(
        val remoteDataSource: T
) : IRepository

open class BaseRepositoryNothing() : IRepository