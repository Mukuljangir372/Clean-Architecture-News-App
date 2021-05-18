package com.mu.jan.sparknews.presentation.news

import androidx.lifecycle.MutableLiveData
import com.mu.jan.sparknews.data.api.NewsApiService
import com.mu.jan.sparknews.data.model.NewsResponse
import com.mu.jan.sparknews.data.model.Response
import com.mu.jan.sparknews.utils.Const
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsRepo(private val service: NewsApiService) {
    fun getTopHeadlines(mLiveData: MutableLiveData<Response<NewsResponse>>){
        subscribe(service.getTopHeadlines(Const.NewsApiKey,Const.IndiaCountryCode),mLiveData)
    }
    fun getHeadlines(category: String,mLiveData: MutableLiveData<Response<NewsResponse>>){
        subscribe(service.getTopHeadlinesByCategory(Const.NewsApiKey
            ,Const.IndiaCountryCode,category)
            ,mLiveData)

    }
    private fun subscribe(observable: Observable<NewsResponse>
                          ,mLiveData: MutableLiveData<Response<NewsResponse>>){
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<NewsResponse> {
                override fun onSubscribe(d: Disposable?) {
                    mLiveData.value = Response.loading()
                }
                override fun onNext(t: NewsResponse?) {
                    t?.let {
                        mLiveData.value = Response.success(it)
                    }
                }
                override fun onError(e: Throwable?) {
                    e?.let {
                        mLiveData.value = Response.error(it.localizedMessage)
                    }
                }
                override fun onComplete() {
                }
            })
    }
}