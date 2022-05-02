package com.joma.youtubeparcer.data.network

import com.joma.youtubeparcer.BuildConfig.API_KEY
import com.joma.youtubeparcer.domain.model.playlist.PlayList
import com.joma.youtubeparcer.domain.model.playlistIem.PlaylistVideos
import com.joma.youtubeparcer.utils.CHANNEL_ID
import com.joma.youtubeparcer.utils.MAX_RESULT
import com.joma.youtubeparcer.utils.PART
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String = PART,
        @Query("channelId") channelId: String = CHANNEL_ID,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") partItems: String = PART,
        @Query("playlistId") playlistId: String?,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT
    ): Response<PlaylistVideos>
}