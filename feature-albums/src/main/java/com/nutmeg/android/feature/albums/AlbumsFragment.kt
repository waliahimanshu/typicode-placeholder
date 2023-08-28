package com.nutmeg.android.feature.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutmeg.android.feature.albums.paging.loading.AlbumLoadingStateAdapter
import com.nutmeg.android.feature.photos.R
import com.nutmeg.android.feature.photos.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private var albumsAdapter: AlbumsAdapter? = null
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_albums, container, false)
        _binding = FragmentAlbumsBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albumItems.collectLatest {
                    albumsAdapter?.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albumsUIState.collectLatest(::onUiState)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.albumsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            albumsAdapter = AlbumsAdapter()
            adapter = albumsAdapter?.withLoadStateHeaderAndFooter(
                header = AlbumLoadingStateAdapter { albumsAdapter?.retry() },
                footer = AlbumLoadingStateAdapter { albumsAdapter?.retry() }
            )
        }
    }

    private fun onUiState(uiStates: AlbumsUIStates) {
        when (uiStates) {
            AlbumsUIStates.Error -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.generic_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        albumsAdapter = null
    }
}