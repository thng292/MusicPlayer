package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "albums",
    indices = [
        Index("albumname", unique = false)
    ]
)
data class Album(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "albumname") val name: String,
    @ColumnInfo(name = "albumartist") val albumartist: String,
    @ColumnInfo(name = "description") val desc: String = "",
    @ColumnInfo(name = "nosong") val nosong: Int,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "count") val count: Long,
    @ColumnInfo(name = "isfavoritealbum") val isfavorite: Boolean,
    @ColumnInfo(name = "source") val source: String,
)
