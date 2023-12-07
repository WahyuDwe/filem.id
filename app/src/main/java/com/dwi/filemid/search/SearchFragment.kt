package com.dwi.filemid.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dwi.filemid.databinding.FragmentSearchBinding
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.ui.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView = binding.searchView
        val searchBar = binding.searchBar

        searchAdapter = MovieAdapter()
//        binding.searchView.editText.setOnEditorActionListener { textView, i, keyEvent ->
//            if (i == EditorInfo.IME_ACTION_SEARCH) {
//                Toast.makeText(context, textView.text, Toast.LENGTH_SHORT).show()
//                searchBar.text = searchView.text
//                searchView.hide()
//                false
//            } else {
//                true
//            }
//
//        }
//        searchView
//            .editText
//            .setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    searchViewModel.getSearchMovies(v?.text.toString())
//                        .observe(viewLifecycleOwner) { search ->
//                            if (search != null) {
//                                when (search) {
//                                    is Resource.Loading -> binding.progressbar.show()
//
//                                    is Resource.Success -> {
//                                        binding.progressbar.visibility = View.GONE
//                                        search.data?.let { searchAdapter.setData(it) }
//                                    }
//
//                                    is Resource.Error -> {
//                                        binding.progressbar.hide()
//                                        Log.e("MainActivity", "onCreate: ${search.msg}")
//                                    }
//                                }
//                            }
//                        }
//                    searchBar.text = searchView.text
//                    searchView.hide()
//                    false
//                } else {
//                    true
//                }
//
//            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}