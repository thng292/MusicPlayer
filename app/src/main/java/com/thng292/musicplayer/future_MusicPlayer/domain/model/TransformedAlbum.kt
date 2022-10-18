package com.thng292.musicplayer.future_MusicPlayer.domain.model

data class TransformedAlbum(
    val id: Long = 0,
    val albumname: String,
    val albumartist: String,
    val description: String = "",
    val nosong: Int,
    val year: Int,
    val thumbnailuri: String,
    val count: Long,
    val isfavoritealbum: Boolean,
    val source: String,
)
