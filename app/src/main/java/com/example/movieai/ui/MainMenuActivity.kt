package com.example.movieai.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieai.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieai.adapter.MovieAdapter
import com.example.movieai.databinding.ActivityMainMenuBinding

import com.example.movieai.models.Movie
import com.example.movieai.viewmodel.HomePageViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

private lateinit var binding: ActivityMainMenuBinding



@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView1)
        movieAdapter= MovieAdapter()
        recyclerView.adapter=movieAdapter
        val lmHorizontal=LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager=lmHorizontal
        viewModel.getObserverLiveData().observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie) {
                t?.let { movie ->
                    movieAdapter.setLists(movie.results)
                }
            }
        })


        viewModel.loadPopularData("1")
    }
}