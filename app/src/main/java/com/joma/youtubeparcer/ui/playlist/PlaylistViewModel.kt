package com.joma.youtubeparcer.ui.playlist

import androidx.lifecycle.ViewModel
import com.joma.youtubeparcer.data.repo.PlaylistRepository

class PlaylistViewModel(repo: PlaylistRepository) : ViewModel() {
    val liveData = repo.getPlaylists()
}