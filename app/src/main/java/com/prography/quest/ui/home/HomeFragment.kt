package com.prography.quest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.FragmentHomeBinding
import com.prography.quest.ui.randomphoto.RandomPhotoFragmentDirections
import com.prography.quest.util.hide
import dagger.hilt.android.AndroidEntryPoint
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

        homeViewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter.submitList(it)
        }

        lifecycleScope.launch {
            homeViewModel.bookmarkPhoto.collectLatest {
                bookmarkAdapter.submitList(it)
                if (bookmarkAdapter.itemCount <= 0) {
                    binding.bookmarkHeader.hide(requireActivity())
                    binding.BookmarkRecyclerView.hide(requireActivity())
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