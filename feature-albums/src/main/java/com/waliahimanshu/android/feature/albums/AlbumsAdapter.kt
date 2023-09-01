package com.waliahimanshu.android.feature.albums

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel

class AlbumsAdapter : PagingDataAdapter<AlbumDetailUIModel, AlbumsDetailViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsDetailViewHolder {
        return AlbumsDetailViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AlbumsDetailViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<AlbumDetailUIModel>() {
            override fun areItemsTheSame(
                oldItem: AlbumDetailUIModel,
                newItem: AlbumDetailUIModel
            ): Boolean =
                oldItem.albumId == newItem.albumId

            override fun areContentsTheSame(
                oldItem: AlbumDetailUIModel,
                newItem: AlbumDetailUIModel
            ): Boolean =
                oldItem == newItem
        }
    }
}

