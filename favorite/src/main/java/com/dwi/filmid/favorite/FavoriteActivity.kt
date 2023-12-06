package com.dwi.filmid.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dwi.filemid.detail.DetailActivity
import com.dwi.filmid.core.domain.model.Movies
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

        setUpAdapter()
        favoriteViewModel.favoriteMovies.observe(this) { favorite ->
            movieAdapter.setData(favorite)
            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movies) {
                    val intent =
                        Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, data.idMovie)
                    startActivity(intent)
                }
            })

        }

    }

    private fun setUpAdapter() {
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}