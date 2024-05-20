package com.example.movieai.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieai.di.retrofit.RetrofitRepository
import com.example.movieai.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(private val repository: RetrofitRepository): ViewModel(){
    var popularMovieList:MutableLiveData<Movie>
    var currentPage = 1

    init {
        popularMovieList= MutableLiveData()
    }
    fun getObserverLiveData():MutableLiveData<Movie>{
        return popularMovieList
    }
    fun loadPopularData(){
        repository.getPopularMovies(currentPage.toString(), popularMovieList)
        currentPage++
    }
}