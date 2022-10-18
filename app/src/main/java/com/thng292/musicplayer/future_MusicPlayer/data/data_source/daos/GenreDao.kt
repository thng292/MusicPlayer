package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.Genre
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedGenre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(genre: Genre)

    @Delete
    suspend fun delete(genre: Genre)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(genre: Genre)

    @Query("SELECT * FROM genres WHERE genrename = :input")
    suspend fun findByName(input: String): List<Genre>

    @Query("SELECT * FROM genres ORDER BY genrename ASC")
    fun getAll(): Flow<List<TransformedGenre>>

    @Query("SELECT COUNT(id) FROM songs WHERE genreid = :genreId")
    suspend fun getNoSong(genreId: Long): Int

    @Query("DELETE FROM genres")
    suspend fun deleteAll()
}