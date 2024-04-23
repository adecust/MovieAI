package com.example.movieai.adapter


import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieai.R
import com.example.movieai.models.*
import com.example.movieai.di.*


import java.util.*

class MovieAdapter(private val isFirstScreen: Boolean = true) :
    RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {


    var liveData: List<Result>? = null

    fun setLists(liveData: List<Result>?) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val posterView = view.findViewById<ImageView>(R.id.posterView)

        fun bind(data: Result, ) {
            Glide.with(posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(posterView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyCustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
    }

    override fun getItemCount(): Int {
        if (liveData == null) {
            return 0
        } else if (isFirstScreen)
            return 4
        else {
            return liveData!!.size
        }
    }
}