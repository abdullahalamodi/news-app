package dev.alamodi.newsapi.data.api

import dev.alamodi.newsapi.data.models.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("news_api.php")
    fun fitchQuakes(): Call<List<News>>
}