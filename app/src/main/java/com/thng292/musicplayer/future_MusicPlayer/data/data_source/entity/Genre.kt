package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "genres", indices = [
    Index("genrename", unique = true)
])
data class Genre(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "genrename") val genrename: String,
)
