package com.example.movieai.ui

import SwipeGestureListener
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieai.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movieai.adapter.MovieAdapter
import com.example.movieai.databinding.ActivityMainMenuBinding

import com.example.movieai.models.Movie
import com.example.movieai.viewmodel.HomePageViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*



@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {
    private lateinit var gestureListener: SwipeGestureListener

    private lateinit var movieAdapter: MovieAdapter
    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView1)
        movieAdapter= MovieAdapter()
        val imageButton=findViewById<ImageButton>(R.id.searchmenuButton);
        recyclerView.adapter=movieAdapter
        val lmHorizontal=LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager=lmHorizontal
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(findViewById(R.id.recyclerView1))

        viewModel.getObserverLiveData().observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie) {
                t.let { movie ->
                    movieAdapter.setLists(movie.results)
                    recyclerView.scrollToPosition(0)
                }
            }
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollHorizontally(1) && dx > 0) { // Check if scrolling to the right and at the end
                    viewModel.loadPopularData()
                }
            }
        })

        viewModel.loadPopularData()  // Initially load data


        imageButton.setOnClickListener {
            val intent = Intent(this, SearchMenuActivity::class.java)
            startActivity(intent)
        }

    }
}