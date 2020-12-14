package dev.alamodi.newsapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.alamodi.newsapi.data.api.NewsApi
import dev.alamodi.newsapi.data.api.NewsFetcher
import dev.alamodi.newsapi.data.models.News

class NewsListViewModel : ViewModel() {
    val newsLiveData:LiveData<List<News>>
    init {
        newsLiveData = NewsFetcher().fitchNews();
    }
}