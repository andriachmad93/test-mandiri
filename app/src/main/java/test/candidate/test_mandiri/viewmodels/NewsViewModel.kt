package test.candidate.test_mandiri.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import test.candidate.test_mandiri.models.NewsModel
import test.candidate.test_mandiri.repositories.NewsRepository

class NewsViewModel constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val newsResponse = MediatorLiveData<NewsModel>()

    fun getNews(searchNews: String): MediatorLiveData<NewsModel> {
        val liveData: LiveData<NewsModel> = newsRepository.getNews(searchNews)

        newsResponse.addSource(liveData, newsResponse::setValue)

        return newsResponse
    }
}
