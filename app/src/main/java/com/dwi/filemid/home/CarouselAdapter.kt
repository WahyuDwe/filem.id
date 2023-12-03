package com.dwi.filemid.home

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dwi.filemid.databinding.ItemCarouselBinding
import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.utils.DiffUtils

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    private var oldList = emptyList<Movies>()

    inner  class CarouselViewHolder(private val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                carouselImageView.load(BuildConfig.IMAGE_URL + item.posterPath)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemCarouselBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(itemCarouselBinding)
    }

    override fun getItemCount(): Int = oldList.size

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)
    }

    fun setData(newList: List<Movies>) {
        val diffCallback = DiffUtils(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}