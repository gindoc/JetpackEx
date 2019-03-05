package com.a3xh1.jetpackex.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
@Module
class HomeFragmentModule{

    @Named("homeString")
    @Provides
    fun provideName(): String {
        return "HomeFragment"
    }

    @Provides
    fun provideNum():Int{
        return 1000
    }
}