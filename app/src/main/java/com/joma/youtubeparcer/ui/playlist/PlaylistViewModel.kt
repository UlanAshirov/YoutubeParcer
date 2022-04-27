package com.joma.youtubeparcer.ui.playlist

import androidx.lifecycle.ViewModel
import com.joma.youtubeparcer.data.implPlaylistRepo.ImplPlaylistRepository
import com.joma.youtubeparcer.domain.playlistUseCase.GetPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(repository: ImplPlaylistRepository) : ViewModel() {
    private val getPlaylistUseCase = GetPlaylistUseCase(repository)

    var liveData = getPlaylistUseCase.getPlaylists()
}