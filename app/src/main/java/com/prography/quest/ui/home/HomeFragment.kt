package com.prography.quest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.FragmentHomeBinding
import com.prography.quest.util.collectLatestStateFlow
import com.prography.quest.util.hide
import com.prography.quest.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var photoPagingAdapter: PhotoPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getPhotosPaging()

        setupRecyclerView()
        observe()
        setupLoadState()
    }

    private fun observe() {
        collectLatestStateFlow(homeViewModel.photosPaging) {
            photoPagingAdapter.submitData(it)
        }

        collectLatestStateFlow(homeViewModel.bookmarkPhoto) {
            when (it) {
                is HomeViewModel.UIState.Loading -> binding.BookmarkRecyclerView.hide(requireActivity())
                is HomeViewModel.UIState.Success -> {
                    bookmarkAdapter.submitList(it.data)
                    bookmarkView(it.data)
                }
                is HomeViewModel.UIState.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        photoPagingAdapter = PhotoPagingAdapter()
        binding.PhotoRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = photoPagingAdapter.withLoadStateFooter(footer = PhotoLoadStateAdapter(photoPagingAdapter)
            )
        }
        photoPagingAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailPhotoDialog(
                BookmarkEntity(it.id, it.description, it.urls.regular, it.user.name, it.user.username)
            )
            findNavController().navigate(action)
        }

        bookmarkAdapter = BookmarkAdapter()
        binding.BookmarkRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bookmarkAdapter
        }
        bookmarkAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailPhotoDialog(it)
            findNavController().navigate(action)
        }
    }

    private fun bookmarkView(data:List<BookmarkEntity>) {
        binding.bookmarkSkeletonUi.stopShimmer()
        binding.bookmarkSkeletonUi.hide(requireActivity())

        if (data.isEmpty()) {
            binding.bookmarkHeader.hide(requireActivity())
            binding.BookmarkRecyclerView.hide(requireActivity())
        } else {
            binding.bookmarkHeader.show(requireActivity())
            binding.BookmarkRecyclerView.show(requireActivity())
        }
    }

    private fun setupLoadState() {
        photoPagingAdapter.addLoadStateListener { combinedLoadStates ->
            val loadState = combinedLoadStates.source
            if (loadState.refresh is LoadState.Loading) {
                binding.PhotoRecyclerView.hide(requireActivity())
            }

            if (loadState.refresh is LoadState.NotLoading) {
                binding.photoSkeletonUi.stopShimmer()
                binding.photoSkeletonUi.hide(requireActivity())
                binding.PhotoRecyclerView.show(requireActivity())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}