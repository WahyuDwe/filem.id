package com.dwi.filmid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.databinding.ItemMoviePopularBinding
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.utils.DiffUtils

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var oldList = emptyList<Movies>()
    private var onItemClickCallback: OnItemClickCallback? = null

    inner class MovieViewHolder(private val binding: ItemMoviePopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                imgPoster.load(BuildConfig.IMAGE_URL + item.posterPath)
            }

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMoviePopularBinding =
            ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMoviePopularBinding)
    }

    override fun getItemCount(): Int = oldList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)
    }

    fun setData(newList: List<Movies>) {
        val diffCallback = DiffUtils(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movies)
    }
}