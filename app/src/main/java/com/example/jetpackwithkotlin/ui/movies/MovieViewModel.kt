package com.example.jetpackwithkotlin.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jetpackwithkotlin.data.MovieDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class MovieViewModel @ViewModelInject constructor(
    private val movieDao:MovieDao
): ViewModel() {


    val search = MutableStateFlow("")

    val sort = MutableStateFlow(SortOrder.BY_NAME)

    val hide_watched = MutableStateFlow(false)
    

    private val moviesFlow = combine(
        search,sort,hide_watched) {
        search,sort,show_fav ->
            Triple(search,sort,show_fav)
        }.flatMapLatest {
            movieDao.getMovies(it.first,it.second,it.third)
        }
    val movies = moviesFlow.asLiveData()

}

enum class SortOrder { BY_NAME,BY_DATE}