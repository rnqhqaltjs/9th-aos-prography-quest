package com.prography.quest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.FragmentHomeBinding
import com.prography.quest.ui.randomphoto.RandomPhotoFragmentDirections
import com.prography.quest.util.UiState
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
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var bookmarkAdapter: BookmarkAdapter

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

        homeViewModel.getPhotos()

        setupRecyclerView()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.photos.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                        binding.sflSample.startShimmer()
                        binding.sflSample.show(requireActivity())
                    }

                    is UiState.Success -> {
                        delay(1000)
                        photoAdapter.submitList(it.data)
                        binding.sflSample.stopShimmer()
                        binding.sflSample.hide(requireActivity())
                    }

                    is UiState.Failure -> {
                        binding.sflSample.stopShimmer()
                        binding.sflSample.hide(requireActivity())
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.bookmarkPhoto.collect {
                    delay(1000)
                    bookmarkAdapter.submitList(it)
                    if (bookmarkAdapter.itemCount <= 0) {
                        binding.bookmarkHeader.hide(requireActivity())
                        binding.BookmarkRecyclerView.hide(requireActivity())
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        photoAdapter = PhotoAdapter()
        binding.PhotoRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = photoAdapter
        }
        photoAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailPhotoDialog(
                BookmarkEntity(
                    it.id,
                    it.description,
                    it.urls.regular,
                    it.user.username
                )
            )
            findNavController().navigate(action)
        }

        bookmarkAdapter = BookmarkAdapter()
        binding.BookmarkRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bookmarkAdapter
        }
        bookmarkAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailPhotoDialog(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}