package com.dwi.filemid.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import com.dwi.filemid.databinding.ActivityDetailBinding
import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import com.google.android.material.appbar.AppBarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.dwi.filemid.R as Rapp
import com.dwi.filmid.core.R as Rcore

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    private var isShow = false
    private var scrollRange = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if (intent.extras != null) {
            val movieId = intent.getIntExtra(EXTRA_DATA, 0)
            detailViewModel.getDetailMovie(movieId).observe(this) { detail ->
                if (detail != null) {
                    when (detail) {
                        is Resource.Loading -> binding.progressbar.show()
                        is Resource.Success -> {
                            binding.progressbar.hide()
                            populateContentDetail(detail.data)
                            showTitleCollapse(detail.data?.title.toString())

                            if (detail.data?.posterPath == null) {
                                binding.ivDetailToolbar.load(Rcore.drawable.ic_placeholder)
                                binding.detailImagePoster.load(Rcore.drawable.ic_placeholder)
                            }
                        }

                        is Resource.Error -> {
                            binding.progressbar.hide()
                            Log.e("DetailActivity", "onCreate: ${detail.msg}")
                        }
                    }
                }
            }
        }
    }

    private fun populateContentDetail(data: Movies?) {
        if (data != null) {
            binding.apply {
                ivDetailToolbar.load(BuildConfig.IMAGE_URL + data.posterPath)
                detailImagePoster.load(BuildConfig.IMAGE_URL + data.posterPath)
                detailTitle.text = data.title
                detailDescription.text = data.overview
                detailScore.text = String.format("%.1f", data.rating)
                detailsReleaseDate.text = data.releaseDate

                var statusFavorite = data.isFavorite
                setStatusFavorite(statusFavorite)
                fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(data, statusFavorite)
                    setStatusFavorite(statusFavorite)

                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.fabFavorite.apply {
            if (statusFavorite) {
                this.setImageDrawable(
                    ContextCompat.getDrawable(this@DetailActivity, Rapp.drawable.ic_favorite)
                )
            } else {
                this.setImageDrawable(
                    ContextCompat.getDrawable(this@DetailActivity, Rapp.drawable.ic_favorite_border)
                )

            }
        }
    }

    private fun showTitleCollapse(title: String) {
        binding.appbar.addOnOffsetChangedListener { appBarLayout: AppBarLayout, verticalOffset: Int ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                binding.collapsingToolbar.title = title
                isShow = true
            } else if (isShow) {
                binding.collapsingToolbar.title = " "
                isShow = false
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}