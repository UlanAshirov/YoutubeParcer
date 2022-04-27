package com.joma.youtubeparcer.ui.playlistItem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joma.youtubeparcer.data.implPlaylistRepo.ImplPlaylistRepository
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.domain.model.playlistIem.MainResponce
import com.joma.youtubeparcer.domain.playlistItemUseCase.GetPlaylistItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistItemViewModel @Inject constructor(repository: ImplPlaylistRepository) :
    ViewModel() {
    private val getPlaylistItemUseCase = GetPlaylistItemUseCase(repository)

    var liveData = MutableLiveData<Resource<MainResponce?>>()

    fun getPlaylistItems(id: String) {
        liveData = getPlaylistItemUseCase.getPlaylistItems(id)
    }
}