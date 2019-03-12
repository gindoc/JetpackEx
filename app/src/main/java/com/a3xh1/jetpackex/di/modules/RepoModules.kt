package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.di.modules.repo.ListRepoModule
import com.a3xh1.jetpackex.di.modules.repo.LoginRepoModule
import dagger.Module

/**
 * Author: GIndoc on 2019/3/6.
 * FOR   :
 */
@Module(
    includes = [
        LoginRepoModule::class,
        ListRepoModule::class
    ]
)
class RepoModules