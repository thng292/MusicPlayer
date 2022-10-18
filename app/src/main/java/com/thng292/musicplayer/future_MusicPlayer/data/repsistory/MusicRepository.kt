package com.thng292.musicplayer.future_MusicPlayer.data.repsistory

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.thng292.musicplayer.R
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.MusicPlayerDatabase
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.*
import com.thng292.musicplayer.future_MusicPlayer.domain.model.*
import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration.Companion.milliseconds


class MusicRepository(): MusicRepoStandard {
    private lateinit var db: MusicPlayerDatabase
    private lateinit var songAccess: SongDao
    private lateinit var artistAccess: ArtistDao
    private lateinit var albumAccess: AlbumDao
    private lateinit var genreAccess: GenreDao
    private lateinit var dirAccess: DirectoryDao
    private lateinit var thumnailAccess: ThumbnailUriDao
    private lateinit var context: Context

    override fun buildDatabase(context1: Context) {
        context = context1
        db = MusicPlayerDatabase.getInstance(context1)
        songAccess = db.songDao()
        artistAccess = db.artistDao()
        albumAccess = db.albumDao()
        genreAccess = db.genreDao()
        dirAccess = db.directoryDao()
        thumnailAccess = db.thumbnailUriDao()
    }

    override fun getAllAlbum(): Flow<List<TransformedAlbum>> {
        return albumAccess.getAll()
    }

    override fun getAllDir(): Flow<List<TransformedDirectory>> {
        return dirAccess.getAll()
    }

    override fun getAllGenre(): Flow<List<TransformedGenre>> {
        return genreAccess.getAll()
    }

    override fun getAllArtist(): Flow<List<TransformedArtist>> {
        return artistAccess.getAll()
    }
    override fun getAllSongs(): Flow<List<TransformedSong>> {
        return songAccess.getAllJoined()
    }

    override fun getAllSongsByAlbum(id: Long): Flow<List<TransformedSong>> {
        return songAccess.getAllJoinedByAlbum(id)
    }

    override fun getAllSongsByArtist(id: Long): Flow<List<TransformedSong>> {
        return songAccess.getAllJoinedByArtist(id)
    }

    override fun getAllSongsByDir(id: Long): Flow<List<TransformedSong>> {
        return songAccess.getAllJoinedByDirectory(id)
    }

    override fun getAllSongsByGenre(id: Long): Flow<List<TransformedSong>> {
        return songAccess.getAllJoinedByGenre(id)
    }

    override fun getAllAlbumByArtist(id: Long): Flow<List<TransformedAlbum>> {
        return albumAccess.getByArtist(id)
    }

    override suspend fun setUpDatabase() {
        if (dirAccess.testDb() > 0) {
            return
        } else {
            loadSongFromDisk()
        }
    }

    override suspend fun loadSongFromDisk(biggerThanKB: Long, longerThanSec: Long) {
//        val targetCollectionUri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            MediaStore.Video.Media.getContentUri(
//                MediaStore.VOLUME_EXTERNAL
//            )
//        } else {
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        }
        val targetCollectionUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val albumArts = Uri.parse("content://media/external/audio/albumart")
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.GENRE,
            MediaStore.Audio.Media.GENRE_ID,
            MediaStore.Audio.Media.RELATIVE_PATH,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.ALBUM_ARTIST,
            MediaStore.Audio.Media.NUM_TRACKS,
            MediaStore.Audio.Media.SIZE,
        )

        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        val query: Cursor? = context.contentResolver.query(
            targetCollectionUri,
            projection,
            null,
            null,
            sortOrder
        )
        var cnt: Long = 0
        var lastDir = ""
        Log.v("test", "OK to here 3")
        query?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val artistIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID)
            val albumCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val albumArtistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ARTIST)
            val albumIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val durationCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val yearCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR)
            val genreCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.GENRE)
            val genreIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.GENRE_ID)
            val pathCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH)
            val trackCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TRACK)
            val noSongCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.NUM_TRACKS)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
            while (cursor.moveToNext()) {
                val albumArtUri =Uri.withAppendedPath(Uri.parse("content://media/external/audio/albumart"), (cursor.getLongOrNull(albumIdCol) ?: 0).toString())
                Log.v("song name", cursor.getString(titleCol))
                if (cursor.getString(pathCol) != lastDir) {
                    cnt++
                    lastDir = cursor.getString(pathCol)
                }
                if ((cursor.getLong(sizeCol)/1024 > biggerThanKB) && (cursor.getLong(durationCol).milliseconds.inWholeSeconds >longerThanSec)) {
                    songAccess.insert(
                        Song(
                            id = cursor.getLongOrNull(idCol) ?: 0,
                            name = cursor.getStringOrNull(titleCol) ?: "",
                            artistId = cursor.getLongOrNull(artistIdCol) ?: 0,
                            albumId = cursor.getLongOrNull(albumIdCol) ?: 0,
                            genreId = cursor.getLongOrNull(genreIdCol) ?: 0,
                            dirId = cnt,
                            track = cursor.getIntOrNull(trackCol) ?: 0,
                            year = cursor.getIntOrNull(yearCol) ?: 0,
                            uri = ContentUris.withAppendedId(targetCollectionUri, cursor.getLong(idCol)).toString(),
                            lengthInSec = cursor.getLongOrNull(durationCol) ?: 0,
                            count = 0,
                            isfavorite = false,
                            source = "This device"
                        )
                    )
                    albumAccess.insert(
                        Album(
                            id = cursor.getLongOrNull(albumIdCol) ?: 0,
                            name = cursor.getStringOrNull(albumCol)
                                ?: context.getString(R.string.unknown),
                            albumartist = cursor.getStringOrNull(albumArtistCol) ?: "Unknown",
                            desc = "",
                            nosong = cursor.getIntOrNull(noSongCol) ?: 1,
                            year = cursor.getIntOrNull(yearCol) ?: 0,
                            count = 0,
                            isfavorite = false,
                            source = "This device"
                        )
                    )
                    artistAccess.insert(
                        Artist(
                            id = cursor.getLongOrNull(artistIdCol) ?: 0,
                            name = cursor.getStringOrNull(artistCol)
                                ?: context.getString(R.string.unknown),
                            desc = "",
                            thumbnailUriId = cursor.getLongOrNull(albumIdCol) ?: 0,
                        )
                    )
                    dirAccess.insert(
                        Directory(
                            id = cnt,
                            dir = (cursor.getStringOrNull(pathCol) ?: "").split('/').last(),
                        )
                    )
                    genreAccess.insert(
                        Genre(
                            id = cursor.getLongOrNull(genreIdCol) ?: 0,
                            genrename = cursor.getStringOrNull(genreCol) ?: "Unknown",
                        )
                    )
                    thumnailAccess.insert(
                        ThumbnailUri(
                            id = cursor.getLongOrNull(albumIdCol) ?: 0,
                            uri = albumArtUri.toString()
                        )
                    )
                }
            }
        }
    }

    override suspend fun resetDatabase() {
        songAccess.deleteAll()
        artistAccess.deleteAll()
        albumAccess.deleteAll()
        genreAccess.deleteAll()
        dirAccess.deleteAll()
        thumnailAccess.deleteAll()
    }
}