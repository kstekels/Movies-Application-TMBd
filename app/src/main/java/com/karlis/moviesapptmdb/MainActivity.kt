package com.karlis.moviesapptmdb

import android.app.DownloadManager
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.karlis.moviesapptmdb.databinding.ActivityMainBinding
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var responseResultString: String
    private var responseErrorString: String? = null
    private lateinit var moviesList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        

        val apiKey = "73619d549f33ccdf0116452a1f3f9427"
        val urlString = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey&language=en-US&page=1"

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlString,
            { responseString ->
                // Response
                this.responseResultString = responseString

                // Json
                val json = JSONObject(responseString)
                Log.d("JSON OBJECT -->>", json.getJSONArray("results").toString())

                val numberOfItems = json.getJSONArray("results").length().toString().toInt()

                moviesList = ArrayList()
                for (index in 0 until numberOfItems) {

                    val posterPath = json.getJSONArray("results").getJSONObject(index).getString("poster_path")
                    val releaseDate = json.getJSONArray("results").getJSONObject(index).getString("release_date")
                    val title = json.getJSONArray("results").getJSONObject(index).getString("title")
                    val voteAverage = json.getJSONArray("results").getJSONObject(index).getString("vote_average")
                    val backdropPath = json.getJSONArray("results").getJSONObject(index).getString("backdrop_path")
                    val overview = json.getJSONArray("results").getJSONObject(index).getString("overview")

                    val totalPages = json.getString("total_pages")

                    Log.d("Pages", totalPages)

                    val movie = Movie(
                        title = title,
                        release_date = releaseDate,
                        vote_average = "Rating: $voteAverage",
                        poster_path = "https://image.tmdb.org/t/p/w500$posterPath",
                        backdrop_path = "https://image.tmdb.org/t/p/w500$backdropPath",
                        overview = overview
                    )
                    moviesList.add(movie)
                }
                binding.moviesListView.adapter = MovieAdapter(this, moviesList)
                binding.moviesListView.isClickable = true

                binding.moviesListView.setOnItemClickListener { parent, view, position, id ->
                    val movieImagePath = moviesList[position].poster_path
                    val movieTitleString = moviesList[position].title
                    val movieReleaseDateString = moviesList[position].release_date
                    val movieRatingAverage = moviesList[position].vote_average
                    val backdropImagePath = moviesList[position].backdrop_path
                    val overviewString = moviesList[position].overview

                    val i = Intent(this, DetailedView::class.java)
                    i.putExtra("PosterImagePath", movieImagePath)
                    i.putExtra("MovieTitle", movieTitleString)
                    i.putExtra("ReleaseDate", "Release date: $movieReleaseDateString")
                    i.putExtra("Rating", movieRatingAverage)
                    i.putExtra("backdropImagePath", backdropImagePath)
                    i.putExtra("overview", overviewString)

                    startActivity(i)

                }

            },
            { volleyError ->
                // Error
                this.responseErrorString = volleyError.message
            }
        )
        Volley.newRequestQueue(this).add(stringRequest)

    }



}