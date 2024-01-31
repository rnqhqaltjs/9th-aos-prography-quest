package com.prography.quest.ui.randomphoto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.databinding.ItemPhotoRandomBinding

class RandomPhotoAdapter : ListAdapter<PhotosResponseItem, RandomPhotoAdapter.RandomPhotoViewHolder>(PhotoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomPhotoViewHolder {
        return RandomPhotoViewHolder(
            ItemPhotoRandomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RandomPhotoViewHolder, position: Int) {
        val itemView = currentList[position]
        holder.bind(itemView)
    }

    inner class RandomPhotoViewHolder(private val binding: ItemPhotoRandomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemView: PhotosResponseItem) {
            binding.letterBox.load(itemView.urls.regular)

            binding.bookmarkBtn.setOnClickListener {
                onBookmarkClickListener?.let { it(itemView, adapterPosition) }
            }

            binding.exitBtn.setOnClickListener {
                val newList = currentList.toMutableList()
                newList.removeAt(adapterPosition)
                submitList(newList)
            }

            binding.informationBtn.setOnClickListener {
                onInformationClickListener?.let { it(itemView) }
            }
        }
    }

    private var onBookmarkClickListener: ((PhotosResponseItem, Int) -> Unit)? = null
    fun setOnBookmarkClickListener(listener: (PhotosResponseItem, Int) -> Unit) {
        onBookmarkClickListener = listener
    }

    private var onInformationClickListener: ((PhotosResponseItem) -> Unit)? = null
    fun setOnInformationClickListener(listener: (PhotosResponseItem) -> Unit) {
        onInformationClickListener = listener
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

