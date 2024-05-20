package com.example.movieai.di.retrofit


import com.example.movieai.models.*

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getRecentVideos(@Query("page") query: String): Call<Movie>

    @GET("3/genre/movie/list?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/videos?api_key=802b2c4b88ea1183e50e6b285a27696e")
    fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>

    @GET("3/search/movie?api_key=802b2c4b88ea1183e50e6b285a27696e&language=en-US")
    fun getSuggestions(@Query("query") query: String): Call<Movie>


    //https://api.themoviedb.org/3/discover/movie?api_key=XXXXX&with_genres=27
}