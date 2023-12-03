package com.dwi.filemid.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dwi.filemid.databinding.ActivityMainBinding
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.ui.MovieAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carouselAdapter = CarouselAdapter()
        movieAdapter = MovieAdapter()

        mainViewModel.nowPlayingMovies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressbar.show()
                    is Resource.Success -> {
                        binding.progressbar.hide()
                        movies.data?.let { carouselAdapter.setData(it) }
                    }

                    is Resource.Error -> {
                        binding.progressbar.hide()
                        Log.e("MainActivity", "onCreate: ${movies.msg}")
                    }
                }
            }
        }

        mainViewModel.popularMovies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressbar.show()
                    is Resource.Success -> {
                        binding.progressbar.hide()
                        movies.data?.let { movieAdapter.setData(it) }
                    }
                    is Resource.Error -> {
                        binding.progressbar.hide()
                        Log.e("MainActivity", "onCreate: ${movies.msg}")
                    }
                }
            }
        }

        setUpCarouselAdapter()
        setUpPopularMoviesAdapter()
    }

    private fun setUpCarouselAdapter() {
        with(binding.nowplayingContent.rvCarousel) {
            layoutManager = CarouselLayoutManager()
            setHasFixedSize(true)
            this.adapter = carouselAdapter
        }
    }

    private fun setUpPopularMoviesAdapter() {
        with(binding.popularContent.rvMovie) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }
}