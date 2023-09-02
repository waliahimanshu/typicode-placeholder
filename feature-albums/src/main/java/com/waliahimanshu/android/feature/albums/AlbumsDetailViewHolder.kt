package com.waliahimanshu.android.feature.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import com.waliahimanshu.android.feature.photos.databinding.AlbumListItemsBinding

class AlbumsDetailViewHolder(private val binding: AlbumListItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AlbumDetailUIModel) {
        binding.composeView.setContent {
            AppTheme() {
                AlbumItem(item = item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): AlbumsDetailViewHolder {
            val albumListItemsBinding =
                AlbumListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AlbumsDetailViewHolder(albumListItemsBinding)
        }
    }
}