package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos

import androidx.room.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.Album
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedAlbum
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(album: Album)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(album: Album)

    @Delete()
    suspend fun delete(album: Album)

    @Query("""
        SELECT albums.id, albums.albumname, albums.albumartist, albums.description, albums.nosong, albums.year, thumbnailsuri.thumbnailuri, albums.count, albums.isfavoritealbum, albums.source
        FROM (albums LEFT JOIN thumbnailsuri ON albums.id = thumbnailsuri.id) 
        ORDER BY albumname ASC
        """)
    fun getAll(): Flow<List<TransformedAlbum>>

    @Query("""
        SELECT albums.id, albums.albumname, albums.albumartist, albums.description, albums.nosong, albums.year, thumbnailsuri.thumbnailuri, albums.count, albums.isfavoritealbum, albums.source
        FROM (albums LEFT JOIN thumbnailsuri ON albums.id = thumbnailsuri.id)
        WHERE albums.id = :id
        ORDER BY albums.year DESC
    """)
    fun getByArtist(id: Long): Flow<List<TransformedAlbum>>

    @Query("SELECT * FROM albums WHERE albumname = :input")
    suspend fun findByName(input: String): List<Album>

    @Query("SELECT * FROM albums LIMIT :n")
    suspend fun getSome(n: Int): List<Album>

    @Query("SELECT SUM(length) FROM songs")
    suspend fun getLengthInMiliSec(): Long

    @Query("SELECT COUNT(id) FROM songs WHERE albumid = :albumId")
    fun getNoSong(albumId: Long): Int

    @Query("DELETE FROM albums")
    fun deleteAll()
}