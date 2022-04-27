package com.joma.youtubeparcer.data.implPlaylistRepo

import androidx.lifecycle.MutableLiveData
import com.joma.youtubeparcer.data.network.ApiService
import com.joma.youtubeparcer.domain.common.Resource
import com.joma.youtubeparcer.domain.model.playlist.PlayList
import com.joma.youtubeparcer.domain.model.playlistIem.MainResponce
import com.joma.youtubeparcer.domain.repo.PlaylistRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ImplPlaylistRepository @Inject constructor(private val api: ApiService) : PlaylistRepository {

    override fun getPlaylists(): MutableLiveData<Resource<PlayList?>> {
        val liveData = MutableLiveData<Resource<PlayList?>>()
        liveData.value = Resource.loading()
        api.getPlaylists().enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    liveData.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                liveData.value = Resource.error(t.message)
            }
        })
        return liveData
    }

    override fun getPlaylistItems(id: String?): MutableLiveData<Resource<MainResponce?>> {
        val liveData = MutableLiveData<Resource<MainResponce?>>()
        liveData.value = Resource.loading()
        api.getPlaylistItems(playlistId = id).enqueue(object : Callback<MainResponce> {
            override fun onResponse(
                call: Call<MainResponce>,
                response: Response<MainResponce>
            ) {
                if (response.isSuccessful) {
                    liveData.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<MainResponce>, t: Throwable) {
                liveData.value = Resource.error(t.message)
            }
        })
        return liveData
    }
}