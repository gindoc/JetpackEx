package com.a3xh1.jetpackex.di.modules

import com.a3xh1.basecore.common.gson.GsonDConverterFactory
import com.a3xh1.jetpackex.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */
private const val TIME_OUT_SECONDS = 10
private const val BASE_URL = "http://jzyd.jiuzhenyidao.com/index.php/api/"

@Module
class HttpClientModule {

    @Provides
    fun provideRetrofit(client: OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonDConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideHttpClient(@Named("httpLogger") loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(
                TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Named("httpLogger")
    fun providesHttpLogger(): HttpLoggingInterceptor {
        val interceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> com.a3xh1.basecore.utils.logger.log { message } })
        val basic = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        interceptor.level = basic
        return interceptor
    }
}