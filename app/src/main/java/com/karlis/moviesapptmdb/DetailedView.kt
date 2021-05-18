package com.karlis.moviesapptmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karlis.moviesapptmdb.databinding.DetailedViewBinding
import com.squareup.picasso.Picasso

class DetailedView: AppCompatActivity() {
    private lateinit var binding: DetailedViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backdropImage = intent.getStringExtra("backdropImagePath")
        val movieTitle = intent.getStringExtra("MovieTitle")
        val moviePosterImage = intent.getStringExtra("PosterImagePath")
        val movieReleaseDate = intent.getStringExtra("ReleaseDate")
        val movieRating = intent.getStringExtra("Rating")
        val overviewText = intent.getStringExtra("overview")


        Picasso.get().load(backdropImage).into(binding.moviePoster1)
        Picasso.get().load(moviePosterImage).into(binding.moviePoster2)
        binding.movieBigTitle.text = movieTitle
        binding.movieReleaseDateDetails.text = movieReleaseDate
        binding.movieRating.text = movieRating
        binding.movieDescription.text = overviewText


    }

}