package com.mu.jan.sparknews.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.jan.sparknews.data.model.NewsResponse
import com.mu.jan.sparknews.data.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private var repo: NewsRepo
) : ViewModel(){
    private var mLiveNewsResult: MutableLiveData<Response<NewsResponse>> = MutableLiveData()

    fun getNews(category: String): MutableLiveData<Response<NewsResponse>>{

        viewModelScope.launch {
            //fetching
            repo.getHeadlines(category,mLiveNewsResult)
        }

        return mLiveNewsResult
    }

}