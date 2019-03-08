package com.a3xh1.jetpackex.base

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.a3xh1.base.BaseApplication
import com.a3xh1.jetpackex.BuildConfig
import com.a3xh1.jetpackex.di.component.DaggerAppComponent
import com.a3xh1.utils.logger.initLogger
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
class App : BaseApplication(), HasActivityInjector {



    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)

        initLogger(BuildConfig.DEBUG)


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}