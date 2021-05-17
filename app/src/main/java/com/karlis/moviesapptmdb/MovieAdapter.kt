package com.karlis.moviesapptmdb

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Activity, private val arrayList: ArrayList<Movie>): ArrayAdapter<Movie>(context, R.layout.movie_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.movie_item, null)

        val moviePoster : ImageView = view.findViewById(R.id.movieImage)
        val movieTitle : TextView = view.findViewById(R.id.movieTitle)
        val movieReleaseDate : TextView = view.findViewById(R.id.movieReleaseDate)
        val movieRating : TextView = view.findViewById(R.id.movieRating)

        movieTitle.text = arrayList[position].title
        movieReleaseDate.text = arrayList[position].release_date
        movieRating.text = arrayList[position].vote_average
        Picasso.get().load(arrayList[position].poster_path).into(moviePoster)

        return view
    }
}