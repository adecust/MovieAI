package com.example.movieai.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieai.di.retrofit.RetrofitRepository
import com.example.movieai.models.GenreX
import com.example.movieai.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(private val repository: RetrofitRepository): ViewModel(){
    var popularMovieList:MutableLiveData<Movie>
    var genreMovieList: MutableLiveData<List<Movie>> = MutableLiveData()
    var genreList: MutableLiveData<List<GenreX>> = MutableLiveData() // LiveData türü güncellendi
    fun loadGenres() {
        popularMovieList = MutableLiveData()

        repository.getAllGenres(genreList)
    }


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
    fun getGenreListLiveData(): MutableLiveData<List<GenreX>> {
        return genreList
    }
}