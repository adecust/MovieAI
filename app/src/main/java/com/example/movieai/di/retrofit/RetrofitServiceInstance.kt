package com.example.movieai.di.retrofit


import com.example.movieai.models.*

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {
    @GET("3/movie/11?api_key=54fee14b5c1475665226f58cafeb9cca")
    fun getPopularVideos (@Query("page") query: String): Call<Movie>
    @GET("3/discover/movie?api_key=54fee14b5c1475665226f58cafeb9cca")
    fun getRecentVideos (@Query("page") query: String): Call<Movie>
    @GET("3/genre/movie/list?api_key=54fee14b5c1475665226f58cafeb9cca")
    fun getGenres(): Call<Genre>
    @GET("3/movie/{id}/videos?api_key=54fee14b5c1475665226f58cafeb9cca")
    fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>
    @GET("3/search/movie?api_key=54fee14b5c1475665226f58cafeb9cca")
    fun getTrailerTeasers(@Query("query") query: String): Call<Movie>

}