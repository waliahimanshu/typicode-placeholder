package com.waliahimanshu.android.feature.albums

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
import androidx.paging.compose.collectAsLazyPagingItems
import com.waliahimanshu.android.feature.albums.ui.theme.AlbumList
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import com.waliahimanshu.android.feature.photos.R
import com.waliahimanshu.android.feature.photos.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albumItems.collectLatest {
                    setComposeListView()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albumsUIState.collectLatest(::onUiState)
            }
        }
    }

    private fun setComposeListView() {
        binding.albumsComposeView.setContent {
            val pagingItems =
                viewModel.albumItems.collectAsLazyPagingItems()
            AppTheme {
                AlbumList(lazyPagingItems = pagingItems)
            }
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
    }
}