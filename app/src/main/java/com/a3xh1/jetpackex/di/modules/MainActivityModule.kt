package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.MainActivity
import com.a3xh1.jetpackex.view.main.HomeFragment
import dagger.Module
import dagger.Provides


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
@Module
class MainActivityModule {

    @Provides
    fun provideName(): String {
        return MainActivity::class.java.name
    }

    @Provides
    fun provideFragment(): HomeFragment {
        return HomeFragment()
    }

}