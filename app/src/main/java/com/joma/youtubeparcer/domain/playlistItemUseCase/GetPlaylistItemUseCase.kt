package com.joma.youtubeparcer.domain.playlistItemUseCase

import androidx.lifecycle.MutableLiveData
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.domain.model.playlistIem.MainResponce
import com.joma.youtubeparcer.domain.repo.PlaylistRepository

class GetPlaylistItemUseCase(private val repository: PlaylistRepository) {
    fun getPlaylistItems(id:String? = null) : MutableLiveData<Resource<MainResponce?>> {
        return repository.getPlaylistItems(id)
    }
}