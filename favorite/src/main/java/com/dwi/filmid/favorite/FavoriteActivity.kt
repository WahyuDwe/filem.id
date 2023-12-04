package com.dwi.filmid.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dwi.filmid.core.ui.MovieAdapter
import com.dwi.filmid.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        movieAdapter = MovieAdapter()

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        favoriteViewModel.favoriteMovies.observe(this) { favorite ->
            movieAdapter.setData(favorite)
        }

        setUpAdapter()
    }

    private fun setUpAdapter() {
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}