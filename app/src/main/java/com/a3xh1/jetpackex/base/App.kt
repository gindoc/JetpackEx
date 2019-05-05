package com.a3xh1.jetpackex.base

import android.app.Activity
import com.a3xh1.basecore.base.BaseApplication
import com.a3xh1.jetpackex.BuildConfig
import com.a3xh1.jetpackex.di.component.DaggerAppComponent
import com.a3xh1.basecore.utils.logger.initLogger
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
class App : BaseApplication(), HasActivityInjector {

    companion object {
        lateinit var instance:App
    }


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerAppComponent.create().inject(this)

        initLogger(BuildConfig.DEBUG)

        initLeakCanary()

    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}