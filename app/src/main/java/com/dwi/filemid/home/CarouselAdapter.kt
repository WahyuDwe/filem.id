package com.dwi.filemid.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dwi.filemid.databinding.ItemCarouselBinding
import com.dwi.filemid.detail.DetailActivity
import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.R
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.utils.DiffUtils

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    private var oldList = emptyList<Movies>()

    inner class CarouselViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                if (item.posterPath == null) {
                    carouselImageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    carouselImageView.load(R.drawable.ic_placeholder)
                    carouselTextView.text = item.title
                } else {
                    carouselImageView.load(BuildConfig.IMAGE_URL + item.posterPath)
                    carouselTextView.text = item.title
                }
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, item.idMovie)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemCarouselBinding =
            ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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