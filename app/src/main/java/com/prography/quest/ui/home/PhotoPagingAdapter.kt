package com.prography.quest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prography.quest.R
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.databinding.ItemPhotoBinding

class PhotoPagingAdapter : PagingDataAdapter<PhotosResponseItem, PhotoPagingAdapter.PhotoViewHolder>(PhotoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val pagedItem = getItem(position)
        pagedItem?.let { photo ->
            holder.bind(photo)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(photo) }
            }
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemView: PhotosResponseItem) {
            binding.ivPhoto.load(itemView.urls.regular) {
                placeholder(R.drawable.skeleton_loading_img_2)
            }
//            binding.tvPhotoTitle.text = itemView.currentUserCollections.map { it.title }.toString()
        }
    }

    private var onItemClickListener: ((PhotosResponseItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (PhotosResponseItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val PhotoDiffCallback = object : DiffUtil.ItemCallback<PhotosResponseItem>() {
            override fun areItemsTheSame(oldItem: PhotosResponseItem, newItem: PhotosResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotosResponseItem, newItem: PhotosResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

