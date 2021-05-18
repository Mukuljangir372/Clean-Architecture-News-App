package com.mu.jan.sparknews.data.api

import com.mu.jan.sparknews.data.model.NewsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun getWorldTopHeadlines(@Query("apiKey") apiKey: String)
            : Observable<NewsResponse>

    @GET("top-headlines")
    fun getTopHeadlines(@Query("apiKey") apiKey: String,
                        @Query("country") country: String)
    : Observable<NewsResponse>

    @GET("top-headlines")
    fun getTopHeadlinesByCategory(@Query("apiKey") apiKey: String,
                        @Query("country") country: String,
                        @Query("category") category: String)
            : Observable<NewsResponse>


}
