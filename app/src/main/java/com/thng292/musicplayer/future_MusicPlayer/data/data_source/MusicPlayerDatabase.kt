package com.thng292.musicplayer.future_MusicPlayer.data.data_source

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.*


@Database(
    entities = [
        Song::class,
        Artist::class,
        Album::class,
        Genre::class,
        Directory::class,
        ThumbnailUri::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MusicPlayerDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao
    abstract fun genreDao(): GenreDao
    abstract fun directoryDao(): DirectoryDao
    abstract fun thumbnailUriDao(): ThumbnailUriDao

    companion object {
        @Volatile
        private var INSTANCE: MusicPlayerDatabase? = null

        fun getInstance(context: Context): MusicPlayerDatabase {
            Log.v("test", "OK to here 1.2")
            var temp = INSTANCE
            if (temp == null) {
                synchronized(this) {
                    Log.v("test", "OK to here 1.3")
                    val instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MusicPlayerDatabase::class.java,
                        "music.db"
                    ).build()
                    Log.v("test", "OK to here 1.4")
                    INSTANCE = instance
                    return instance
                }
            } else {
                return temp
            }
        }
    }
}