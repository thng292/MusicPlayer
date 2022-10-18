package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "directories",
    indices = [
        Index("dirname", unique = false)
    ]
)
data class Directory(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "dirname") val dir: String,
)
