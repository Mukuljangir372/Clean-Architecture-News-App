package com.mu.jan.sparknews.di

import android.content.Context
import com.mu.jan.sparknews.data.api.NewsApiService
import com.mu.jan.sparknews.helper.NetworkHelper
import com.mu.jan.sparknews.presentation.news.NewsRepo
import com.mu.jan.sparknews.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 * Retrofit - It's a Http client, used for sending or receiving Http network calls.
 * OkHttp - It's a Http client, used for sending or receiving Http network calls.
 * RxAndroid - For modifying, filtering coming data asynchronously
 * with some extra functionalities for authenticate, modifying, retry calls, caching data.
 *
 * Interceptor - Interceptor are the powerful features in OkHttp used for
 * authenticate, modifying, retry calls, caching data.
 *
 * HTTP Header - Used for adding some extra info with HTTP network call request or response.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun okHttpClient(@ApplicationContext mContext: Context): OkHttpClient{
        //cache
        val maxSize = (20 * 1024 * 1024).toLong()
        val dir = File(mContext.cacheDir,"Cache Response")
        val cache = Cache(dir,maxSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor {
                val response = it.proceed(it.request())
                if(NetworkHelper.isNetworkConnected(mContext)){
                    //read from cache for 60 seconds
                    val cacheControl = CacheControl.Builder()
                        .maxAge(60,TimeUnit.SECONDS)
                        .build()
                    response.newBuilder()
                        .header("Cache-Control",cacheControl.toString())
                        .build()
                }else {
                    //cache maxStale for 10 days
                    val cacheControl = CacheControl.Builder()
                        .maxStale(10,TimeUnit.DAYS)
                        .build()
                    response.newBuilder()
                        .header("Cache-Control",cacheControl.toString())
                        .build()
                }
            }
            .build()
    }
    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.NewsBaseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun newsApiService(retrofit: Retrofit): NewsApiService{
        return retrofit.create(NewsApiService::class.java)
    }
    @Provides
    @Singleton
    fun newsRepo(service: NewsApiService): NewsRepo{
        return NewsRepo(service)
    }

}