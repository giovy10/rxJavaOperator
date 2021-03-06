package com.example.rxjavaretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.Result
import com.example.rxjavaretrofit.ui.main.MainScreenDirections

class MoviesAdapter(val movies: List<Result>): RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }
}

class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
    private val title:TextView = itemView.findViewById(R.id.movie_title)
    private val overview:TextView = itemView.findViewById(R.id.movie_overview)
    private val rating:TextView = itemView.findViewById(R.id.movie_rating)
    private val cardMovie: CardView = itemView.findViewById(R.id.cardMovie)

    fun bind(movie: Result) {
        Glide
            .with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(photo)
        title.text = "Title: "+movie.title
        overview.text = movie.overview
        rating.text = "Rating : "+movie.vote_average.toString()

        cardMovie.setOnClickListener {

//           navigation with Bundle

//            val bundle = Bundle()
//            bundle.putInt("movie_id", movie.id)
//            itemView.findNavController().navigate(R.id.detailScreen, bundle)

//          Navigation with safeArgs
            itemView.findNavController().navigate(
                MainScreenDirections.actionMainScreenToDetailScreen(
                    movie.id
                )
            )
        }
    }
}