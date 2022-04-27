package com.joma.youtubeparcer.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joma.youtubeparcer.databinding.PlaylistItemBinding
import com.joma.youtubeparcer.domain.model.playlist.ItemPlayer
import com.joma.youtubeparcer.utils.loadImage

class PlaylistAdapter(private val sendKey: SendKeyPlayList<String>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    private var list: List<ItemPlayer> = ArrayList()

    fun setPlaylist(list: List<ItemPlayer>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding =
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            list[position].id?.let { it1 -> sendKey.onItemClick(it1) }
        }
    }

    override fun getItemCount(): Int = list.size

    class PlaylistViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemPlayer: ItemPlayer) {
            itemPlayer.snippet?.thumb?.medium?.url?.let { binding.imgItemPlaylist.loadImage(it) }
            binding.tvItemDescription.text = itemPlayer.snippet?.title
        }
    }

    interface SendKeyPlayList<T> {
        fun onItemClick(data: T)
    }
}