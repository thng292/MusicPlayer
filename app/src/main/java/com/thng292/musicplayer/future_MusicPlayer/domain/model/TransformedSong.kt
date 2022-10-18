package com.thng292.musicplayer.future_MusicPlayer.domain.model

class TransformedSong(
    val id: Long,
    val songname: String,
    val artistname: String,
    val albumname: String,
    val dirname:String,
    val genrename: String,
    val track: Int,
    val length: String,
    val year: Int,
    val isfavoritesong: Boolean,
    val uri: String,
    val count: Long,
    val thumbnailuri: String,
    val source: String,
)