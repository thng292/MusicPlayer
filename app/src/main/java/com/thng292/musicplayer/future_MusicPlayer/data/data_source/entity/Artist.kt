package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artists",
    indices = [
        Index("artistname", unique = false)
    ]
)
data class Artist(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "artistname") val name: String,
    @ColumnInfo(name = "description") val desc: String = "",
    @ColumnInfo(name = "thumbnailuriid") val thumbnailUriId: Long,
)

