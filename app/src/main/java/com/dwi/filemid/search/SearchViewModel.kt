package com.dwi.filemid.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dwi.filmid.core.domain.usecase.MoviesUseCase

class SearchViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
     fun getSearchMovies(query: String) = moviesUseCase.getSearchMovies(query).asLiveData()
}