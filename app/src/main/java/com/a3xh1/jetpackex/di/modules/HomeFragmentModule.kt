package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.view.main.HomeFragment
import dagger.Module
import dagger.Provides

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
@Module
class HomeFragmentModule{

    @Provides
    fun provideName(): String {
        return HomeFragment::class.java.name
    }
}