package com.example.movieai.di.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieai.models.Genre
import com.example.movieai.models.GenreX
import com.example.movieai.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance : RetrofitServiceInstance) {

    fun getPopularMovies(page: String, liveData: MutableLiveData<Movie>) {
        retrofitServiceInstance.getPopularVideos(page).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
    fun getAllGenres(liveData: MutableLiveData<List<GenreX>>) {
        retrofitServiceInstance.getGenres().enqueue(object : Callback<Genre> {
            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                // Doğru veri atamasını sağlıyoruz.
                liveData.postValue(response.body()?.genres)
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
    fun getRecentMovies(page: String, liveData: MutableLiveData<Movie>) {
        retrofitServiceInstance.getRecentVideos(page).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun getMoviesByGenre(genreId: Int, liveData: MutableLiveData<List<Movie>>) {
        retrofitServiceInstance.moviebyGenre(genreId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()?.let {
                    // Burada varsayıyoruz ki, gelen veri bir liste. Eğer tek bir film dönüyorsa, bu kısmı ona göre değiştirmeniz gerekebilir.
                    liveData.postValue(listOf(it))
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("RetrofitRepository", "Error fetching movies by genre", t)
                liveData.postValue(null)
            }
        })
    }
}