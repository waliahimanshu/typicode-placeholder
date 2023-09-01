package com.waliahimanshu.android.feature.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel
import com.waliahimanshu.android.feature.photos.R
import com.waliahimanshu.android.feature.photos.databinding.AlbumListItemsBinding

class AlbumsDetailViewHolder(private val binding: AlbumListItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AlbumDetailUIModel) {
        binding.albumName.text = item.albumTitle
        binding.userName.text = item.userName

        val context = binding.root.context

        Glide.with(context)
            .load(item.thumbnailUrl)
            .placeholder(R.drawable.baseline_image_24)
            .into(binding.thumbnailImageView)
        binding.titlePhoto.text = item.photoTitle
    }

    companion object {
        fun create(parent: ViewGroup): AlbumsDetailViewHolder {
            val albumListItemsBinding =
                AlbumListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AlbumsDetailViewHolder(albumListItemsBinding)
        }
    }
}