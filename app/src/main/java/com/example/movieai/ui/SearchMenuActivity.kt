package com.example.movieai.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieai.R
import com.example.movieai.adapter.MovieAdapter
import com.example.movieai.models.Movie
import com.example.movieai.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
@AndroidEntryPoint
class SearchMenuActivity : AppCompatActivity(), CoroutineScope {
    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }
    private lateinit var recyclerView: RecyclerView
    private val movieAdapter = MovieAdapter()
    private var job: Job = Job()
    private var currentPage = 1

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_menu)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = movieAdapter

        viewModel.getObserverLiveData().observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie) {
                t?.let { movie ->
                    movieAdapter.setLists(movie.results)
                }
            }
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && dy > 0) { // Check if scrolling down and at the bottom
                    viewModel.loadPopularData()

                }
            }
        })

        viewModel.loadPopularData() // Initially load movies
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    }

