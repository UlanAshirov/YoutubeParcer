package com.joma.youtubeparcer.domain.model.playlistIem

data class PlaylistVideos(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)