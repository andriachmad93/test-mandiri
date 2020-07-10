package test.candidate.test_mandiri.core

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import test.candidate.test_mandiri.models.NewsModel

interface APIList {

    @GET
    fun getNews(@Url url: String, @Query("q") query: String, @Query("apikey") apiKey: String): Call<NewsModel>
}