package com.lonnie.chicsign.myfavoritesongs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.model.Info
import com.lonnie.chicsign.myfavoritesongs.databinding.ItemSongListBinding


class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Info>()

    fun setListData(data: MutableList<Info>) {
        dataList = data
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_song_list, parent, false
            )
        )
    }

    fun isEmptyListItem(): Boolean {
        return dataList.size < 1
    }

    class ViewHolder(
        private val binding: ItemSongListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Info) {
            binding.apply {
                postItem = item
            }
        }
    }
}