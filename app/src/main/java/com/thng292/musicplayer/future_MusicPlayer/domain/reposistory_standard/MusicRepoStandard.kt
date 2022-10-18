package com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard

import android.content.Context
import com.thng292.musicplayer.future_MusicPlayer.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MusicRepoStandard {
    fun buildDatabase(context: Context)

    fun getAllAlbum(): Flow<List<TransformedAlbum>>

    fun getAllDir(): Flow<List<TransformedDirectory>>

    fun getAllGenre(): Flow<List<TransformedGenre>>

    fun getAllArtist(): Flow<List<TransformedArtist>>

    fun getAllSongs(): Flow<List<TransformedSong>>

    fun getAllSongsByAlbum(id: Long): Flow<List<TransformedSong>>

    fun getAllSongsByArtist(id: Long): Flow<List<TransformedSong>>

    fun getAllSongsByDir(id: Long): Flow<List<TransformedSong>>

    fun getAllSongsByGenre(id: Long): Flow<List<TransformedSong>>

    fun getAllAlbumByArtist(id: Long): Flow<List<TransformedAlbum>>

    suspend fun setUpDatabase()

    suspend fun loadSongFromDisk(biggerThanKB: Long = 100L, longerThanSec: Long = 60L)

    suspend fun resetDatabase()
}