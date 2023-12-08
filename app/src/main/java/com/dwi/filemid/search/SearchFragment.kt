package com.dwi.filemid.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dwi.filemid.R
import com.dwi.filemid.databinding.FragmentSearchBinding
import com.dwi.filemid.detail.DetailActivity
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.ui.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.hide()
        binding.lottieViewSearch.visibility = View.VISIBLE
        binding.tvDescription.visibility = View.VISIBLE
        val searchView = binding.searchView
        movieAdapter = MovieAdapter()

        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        searchViewModel.getSearchMovies(query)
                            .observe(viewLifecycleOwner) { movies ->
                                if (movies != null) {
                                    binding.lottieViewSearch.visibility = View.GONE
                                    binding.tvDescription.visibility = View.GONE
                                    when (movies) {
                                        is Resource.Loading -> {
                                            Log.d("SearchFragment", "onViewCreated: Loading")
                                            binding.progressbar.show()
                                        }

                                        is Resource.Success -> {
                                            Log.d("SearchFragment", "onViewCreated: Success")
                                            binding.progressbar.hide()
                                            movies.data?.let { movieAdapter.setData(it) }
                                            movieAdapter.setOnItemClickCallback(object :
                                                MovieAdapter.OnItemClickCallback {
                                                override fun onItemClicked(data: Movies) {
                                                    val intent =
                                                        Intent(activity, DetailActivity::class.java)
                                                    intent.putExtra(
                                                        DetailActivity.EXTRA_DATA, data.idMovie
                                                    )
                                                    startActivity(intent)
                                                }

                                            })

                                            if (movies.data.isNullOrEmpty()) {
                                                binding.viewError.lottieView.visibility =
                                                    View.VISIBLE
                                                binding.viewError.tvError.visibility = View.VISIBLE
                                            }
                                        }

                                        is Resource.Error -> {
                                            Log.d("SearchFragment", "onViewCreated: Error")
                                            binding.progressbar.hide()
                                            binding.viewError.tvError.visibility = View.VISIBLE
                                            binding.viewError.tvError.text =
                                                movies.msg ?: getString(
                                                    R.string.data_filem_tidak_ditemukan
                                                )
                                        }
                                    }
                                }
                            }
                    }
                    // hide keyboard
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(query: String): Boolean = false

            })
        }

        setUpAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }
}