package com.dwi.filemid.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dwi.filemid.databinding.FragmentHomeBinding
import com.dwi.filemid.detail.DetailActivity
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.ui.MovieAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carouselAdapter = CarouselAdapter()
        movieAdapter = MovieAdapter()

        if (activity != null) {
            getNowPlayingMovies()
            getPopularMovies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNowPlayingMovies() {
        homeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { movies ->
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

    private fun getPopularMovies() {
        homeViewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressbar.show()
                    is Resource.Success -> {
                        binding.progressbar.hide()
                        movies.data?.let { movieAdapter.setData(it) }
                        movieAdapter.setOnItemClickCallback(object :
                            MovieAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: Movies) {
                                val intent =
                                    Intent(activity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.EXTRA_DATA, data.idMovie)
                                startActivity(intent)
                            }
                        })
                    }

                    is Resource.Error -> {
                        binding.progressbar.hide()
                        Log.e("MainActivity", "onCreate: ${movies.msg}")
                    }
                }
            }
        }


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
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }
}