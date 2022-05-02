package com.joma.youtubeparcer.ui.playlistItem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joma.youtubeparcer.databinding.ItemVideosPlayerBinding
import com.joma.youtubeparcer.domain.model.playlistIem.Item
import com.joma.youtubeparcer.utils.loadImage

class PlaylistItemAdapter(private val sendId: SendVideoId) :
    RecyclerView.Adapter<PlaylistItemAdapter.PlaylistItemViewHolder>() {
    private var list: List<Item> = ArrayList()

    fun setPlaylist(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        val binding =
            ItemVideosPlayerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return PlaylistItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            val videoId = list[position].contentDetails.videoId
            val videoTitle = list[position].snippet.title
            val videoDes = list[position].snippet.description
            sendId.sendVideoId(videoId, videoTitle, videoDes)
        }
    }

    override fun getItemCount(): Int = list.size

    class PlaylistItemViewHolder(private val binding: ItemVideosPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Item) {
            binding.imgVideo.loadImage(item.snippet.thumbnails.medium.url)
            binding.tvVideoDes.text = item.snippet.title
        }
    }

    interface SendVideoId {
        fun sendVideoId(videoId: String, title: String, des: String)
    }
}