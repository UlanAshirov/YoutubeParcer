package com.joma.youtubeparcer.domain.playlistUseCase

import androidx.lifecycle.MutableLiveData
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.domain.model.playlist.PlayList
import com.joma.youtubeparcer.domain.repo.PlaylistRepository

class GetPlaylistUseCase(private val repository: PlaylistRepository) {
    fun getPlaylists(): MutableLiveData<Resource<PlayList?>> {
        return repository.getPlaylists()
    }
}