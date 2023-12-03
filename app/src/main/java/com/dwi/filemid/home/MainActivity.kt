package com.dwi.filemid.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dwi.filemid.databinding.ActivityMainBinding
import com.dwi.filmid.core.data.source.Resource
import com.google.android.material.carousel.CarouselLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var carouselAdapter: CarouselAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carouselAdapter = CarouselAdapter()

        mainViewModel.movies.observe(this) { movies ->
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

        setUpCarouselAdapter()
    }

    private fun setUpCarouselAdapter() {
        with(binding.rvCarousel) {
            layoutManager = CarouselLayoutManager()
            setHasFixedSize(true)
            this.adapter = carouselAdapter
        }
    }
}