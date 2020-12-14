package dev.alamodi.newsapi.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.alamodi.newsapi.data.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFetcher {
    private val newsApi: NewsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:80/breaking_news-api/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsApi = retrofit.create(NewsApi::class.java)
    }

    fun fitchNews(): LiveData<List<News>> {
        val responseLiveData: MutableLiveData<List<News>> = MutableLiveData()
        val quakeRequest: Call<List<News>> = newsApi.fitchQuakes()
        quakeRequest.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.e("Failure", "Failed to fetch news because: ", t)
            }

            override fun onResponse(
                call: Call<List<News>>,
                response: Response<List<News>>
            ) {
                Log.e("Success", response.code().toString())
                var featuresList: List<News>? = response.body()
                    ?: mutableListOf()

                responseLiveData.value = featuresList
            }
        })
        return responseLiveData
    }
}