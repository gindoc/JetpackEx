package com.a3xh1.jetpackex.di.modules.fragment

import com.a3xh1.jetpackex.view.main.ChildFragment
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

//    @Provides
//    fun provideChildFragment():ChildFragment{
//        return ChildFragment()
//    }
}