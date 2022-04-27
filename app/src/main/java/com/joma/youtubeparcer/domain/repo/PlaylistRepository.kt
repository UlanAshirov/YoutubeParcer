package com.joma.youtubeparcer.domain.repo

import androidx.lifecycle.MutableLiveData
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.domain.model.playlist.PlayList
import com.joma.youtubeparcer.domain.model.playlistIem.MainResponce

interface PlaylistRepository {
    fun getPlaylists(): MutableLiveData<Resource<PlayList?>>
    fun getPlaylistItems(id: String?): MutableLiveData<Resource<MainResponce?>>
}