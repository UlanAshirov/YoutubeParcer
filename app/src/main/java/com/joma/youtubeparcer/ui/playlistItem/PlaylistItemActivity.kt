package com.joma.youtubeparcer.ui.playlistItem

import android.os.Bundle
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModelProvider
import com.joma.youtubeparcer.base.BaseActivity
import com.joma.youtubeparcer.databinding.ActivityPlaylistItemBinding
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.ui.playlistItem.adapter.PlaylistItemAdapter
import com.joma.youtubeparcer.utils.isOnline
import com.joma.youtubeparcer.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistItemActivity :
    BaseActivity<ActivityPlaylistItemBinding>(ActivityPlaylistItemBinding::inflate) {
    private lateinit var viewModel: PlaylistItemViewModel
    private lateinit var adapter: PlaylistItemAdapter

    override fun chekInternet() {
        if (isOnline()) {
            setupUI()
            setupObserver()
        }
    }

    private fun getData() {
        val extras: Bundle? = intent.extras
        val id = extras?.getString("playlistId")
        id?.let { viewModel.getPlaylistItems(it) }
    }

    private fun setupUI() {
        binding.txtMainTitle.text = "HellYeahPlay"
        binding.txtMainDescription.text =
            "Ты попал к 32 летнему деду-мизантропу с психическими расстройствами, который смотрит видосы, любит аниме, Minecraft и детей."
        adapter = PlaylistItemAdapter()
        binding.rvPlayer.adapter = adapter
        viewModel = ViewModelProvider(this)[PlaylistItemViewModel::class.java]
        getData()
    }

    private fun setupObserver() {
        viewModel.liveData.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> it.data?.items?.let { it1 -> adapter.setPlaylist(it1) }
                Resource.Status.LOADING -> showToast("Loading")
                Resource.Status.ERROR -> showToast(it.message + "")
            }
        })
    }
}