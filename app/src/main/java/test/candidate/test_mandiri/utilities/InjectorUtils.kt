package test.candidate.test_mandiri.utilities

import test.candidate.test_mandiri.repositories.NewsRepository
import test.candidate.test_mandiri.viewmodels.NewsViewModelFactory

object InjectorUtils {
    fun provideNewsViewModelFactory(): NewsViewModelFactory {
        val repository = NewsRepository()

        return NewsViewModelFactory(repository)
    }
}