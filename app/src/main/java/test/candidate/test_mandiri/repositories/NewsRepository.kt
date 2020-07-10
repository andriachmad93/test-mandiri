package test.candidate.test_mandiri.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import test.candidate.test_mandiri.core.APIList
import test.candidate.test_mandiri.core.restfull.RetrofitManager
import test.candidate.test_mandiri.models.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    private val mRetrofit = RetrofitManager()
    private var service: APIList = mRetrofit.service

    init {
        mRetrofit.initRetrofit()
    }

    fun getNews(searchNews: String): LiveData<NewsModel> {
        val newsResponse: MutableLiveData<NewsModel> = MutableLiveData()

        service.getNews("everything", searchNews, "9f04d21c4d904bd19770a68ad20da1d6").enqueue(
            object: Callback<NewsModel> {

                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    newsResponse.value = response.body()
                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {}
            }
        )

        return newsResponse
    }
}