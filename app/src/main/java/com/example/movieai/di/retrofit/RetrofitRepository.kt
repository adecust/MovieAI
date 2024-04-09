package com.example.movieai.di.retrofit

import androidx.lifecycle.MutableLiveData
import com.example.movieai.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance : RetrofitServiceInstance) {

    fun getPopulerMovies(page:String,LiveData:MutableLiveData<Movie>){
        retrofitServiceInstance.getPopularVideos(page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                LiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                LiveData.postValue(null)
            }

        })
    }
}