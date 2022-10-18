package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos

import androidx.room.*
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedSong
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.Song
import kotlinx.coroutines.flow.Flow


@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(song: Song)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(song: Song)

    @Delete
    suspend fun delete(song: Song)

    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun getById(id: Long): Song

    @Query(" SELECT * FROM songs ORDER BY songs.songname ASC")
    fun getAll(): Flow<List<Song>>

    @Query("""
        SELECT songs.id, songs.songname, artists.artistname, albums.albumname, directories.dirname, genres.genrename, songs.track, songs.length, songs.year, songs.isfavoritesong, songs.uri, songs.count, thumbnailsuri.thumbnailuri, albums.source   
        FROM(((((songs 
            LEFT JOIN artists ON songs.artistid = artists.id) 
            LEFT JOIN albums ON songs.albumid = albums.id) 
            LEFT JOIN thumbnailsuri ON thumbnailsuri.id = songs.albumid)
            LEFT JOIN directories ON directories.id = songs.dirid)
            LEFT JOIN genres ON songs.genreid = genres.id)
        ORDER BY songs.songname ASC
    """)
    fun getAllJoined(): Flow<List<TransformedSong>>

    @Query("""
        SELECT songs.id, songs.songname, artists.artistname, albums.albumname, directories.dirname, genres.genrename, songs.track, songs.length, songs.year, songs.isfavoritesong, songs.uri, songs.count, thumbnailsuri.thumbnailuri, albums.source
        FROM(((((songs 
            LEFT JOIN artists ON songs.artistid = artists.id) 
            LEFT JOIN albums ON songs.albumid = albums.id) 
            LEFT JOIN thumbnailsuri ON thumbnailsuri.id = songs.albumid)
            LEFT JOIN directories ON directories.id = songs.dirid) 
            LEFT JOIN genres ON songs.genreid = genres.id)
        WHERE songs.albumid = :id
        ORDER BY songs.track ASC
    """)
    fun getAllJoinedByAlbum(id: Long): Flow<List<TransformedSong>>

    @Query("""
        SELECT songs.id, songs.songname, artists.artistname, albums.albumname, directories.dirname, genres.genrename, songs.track, songs.length, songs.year, songs.isfavoritesong, songs.uri, songs.count, thumbnailsuri.thumbnailuri, albums.source
        FROM(((((songs 
            LEFT JOIN artists ON songs.artistid = artists.id) 
            LEFT JOIN albums ON songs.albumid = albums.id) 
            LEFT JOIN thumbnailsuri ON thumbnailsuri.id = songs.albumid)
            LEFT JOIN directories ON directories.id = songs.dirid) 
            LEFT JOIN genres ON songs.genreid = genres.id)
        WHERE songs.artistid = :id
        ORDER BY songs.year DESC
    """)
    fun getAllJoinedByArtist(id: Long): Flow<List<TransformedSong>>

    @Query("""
        SELECT songs.id, songs.songname, artists.artistname, albums.albumname, directories.dirname, genres.genrename, songs.track, songs.length, songs.year, songs.isfavoritesong, songs.uri, songs.count, thumbnailsuri.thumbnailuri, albums.source
        FROM(((((songs 
            LEFT JOIN artists ON songs.artistid = artists.id) 
            LEFT JOIN albums ON songs.albumid = albums.id) 
            LEFT JOIN thumbnailsuri ON thumbnailsuri.id = songs.albumid)
            LEFT JOIN directories ON directories.id = songs.dirid)
            LEFT JOIN genres ON songs.genreid = genres.id)
        WHERE songs.dirid = :id
        ORDER BY songs.songname ASC
    """)
    fun getAllJoinedByDirectory(id: Long): Flow<List<TransformedSong>>

    @Query("""
        SELECT songs.id, songs.songname, artists.artistname, albums.albumname, directories.dirname, genres.genrename, songs.track, songs.length, songs.year, songs.isfavoritesong, songs.uri, songs.count, thumbnailsuri.thumbnailuri, albums.source   
        FROM(((((songs 
            LEFT JOIN artists ON songs.artistid = artists.id) 
            LEFT JOIN albums ON songs.albumid = albums.id) 
            LEFT JOIN thumbnailsuri ON thumbnailsuri.id = songs.albumid)
            LEFT JOIN directories ON directories.id = songs.dirid)
            LEFT JOIN genres ON songs.genreid = genres.id)
        WHERE songs.genreid = :id
        ORDER BY songs.songname ASC
    """)
    fun getAllJoinedByGenre(id: Long): Flow<List<TransformedSong>>


    @Query("DELETE FROM songs")
    suspend fun deleteAll()

    @Query("UPDATE songs SET count = :cnt WHERE id = :id")
    suspend fun increaseCount(id: Long, cnt: Long)
}