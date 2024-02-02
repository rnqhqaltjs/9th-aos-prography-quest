package com.prography.quest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prography.quest.databinding.ItemLoadStateBinding

class PhotoLoadStateAdapter(photoPagingAdapter: PhotoPagingAdapter) : LoadStateAdapter<PhotoLoadStateAdapter.PhotoLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadStateViewHolder {
        return PhotoLoadStateViewHolder(
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    inner class PhotoLoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.NotLoading
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}