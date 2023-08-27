package com.nutmeg.android.feature.albums.paging.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.nutmeg.android.feature.photos.R
import com.nutmeg.android.feature.photos.databinding.LoadingAndErrorListItemBinding

class LoadStateViewHolder(
    private val binding: LoadingAndErrorListItemBinding, private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        binding.retryButton.setOnClickListener {
            retry()
        }

        when (loadState) {
            is LoadState.NotLoading -> {}
            LoadState.Loading -> {
                binding.loadingProgressBar.show()
                errorView(visible = false)
            }

            is LoadState.Error -> {
                errorView(visible = true)
                binding.loadingProgressBar.isVisible = false
            }
        }
    }

    private fun errorView(visible: Boolean) {
        binding.retryButton.isVisible = visible
        binding.errorMessageTextView.apply {
            isVisible = visible
            text = binding.root.context.getString(
                R.string.generic_error_message
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val binding = LoadingAndErrorListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return LoadStateViewHolder(binding, retry)
        }
    }
}