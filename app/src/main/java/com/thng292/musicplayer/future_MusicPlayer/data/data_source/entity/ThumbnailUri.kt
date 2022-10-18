package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "thumbnailsuri",
)
data class ThumbnailUri(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "thumbnailuri") val uri: String,
)
