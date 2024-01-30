package com.prography.quest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.ItemBookmarkBinding

class BookmarkAdapter : ListAdapter<BookmarkEntity, BookmarkAdapter.BookmarkViewHolder>(BookmarkDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val itemView = currentList[position]
        holder.bind(itemView)
    }

    inner class BookmarkViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemView: BookmarkEntity) {
            binding.ivPhoto.load(itemView.imageUrl)
        }
    }

    companion object {
        private val BookmarkDiffCallback = object : DiffUtil.ItemCallback<BookmarkEntity>() {
            override fun areItemsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}

