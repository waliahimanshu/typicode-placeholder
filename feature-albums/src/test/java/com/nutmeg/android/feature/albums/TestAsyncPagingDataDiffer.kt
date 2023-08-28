package com.nutmeg.android.feature.albums

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.nutmeg.android.feature.albums.model.AlbumDetailUIModel
import kotlinx.coroutines.Dispatchers

suspend fun getDataFromPagedData(expectedItem: PagingData<AlbumDetailUIModel>): List<AlbumDetailUIModel> {
    differ.submitData(expectedItem)
    return differ.snapshot().items
}

/**
 * Using AsyncPagingDataDiffer for extracting data from PagedData<>
 * for the purpose of testing
 */
val differ = AsyncPagingDataDiffer(
    diffCallback = TestDiffCallback<AlbumDetailUIModel>(),
    updateCallback = TestListCallback(),
    workerDispatcher = Dispatchers.Main
)


class TestDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

class TestListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}