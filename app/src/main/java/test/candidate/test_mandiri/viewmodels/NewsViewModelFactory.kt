package test.candidate.test_mandiri.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.candidate.test_mandiri.repositories.NewsRepository

class NewsViewModelFactory constructor(private val newsRepository: NewsRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = NewsViewModel(newsRepository) as T
}
