package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos


import androidx.room.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.Artist
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedArtist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: Artist)

    @Query("""
        SELECT artists.id, artists.artistname, artists.description, thumbnailsuri.thumbnailuri 
        FROM (artists LEFT JOIN thumbnailsuri ON artists.thumbnailuriid = thumbnailsuri.id) 
        ORDER BY artistname ASC
        """)
    fun getAll(): Flow<List<TransformedArtist>>
    
    @Delete
    suspend fun delete(artist: Artist)
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(artist: Artist)

    @Query("SELECT * FROM artists WHERE artistname = :input")
    suspend fun findByName(input: String): List<Artist>

    @Query("SELECT * FROM artists LIMIT :n")
    suspend fun getSome(n: Int): List<Artist>

    @Query("SELECT COUNT(id) FROM songs WHERE artistid = :artistId")
    suspend fun getNoSong(artistId: Long): Int

    @Query("DELETE FROM artists")
    suspend fun deleteAll()
}