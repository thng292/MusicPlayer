package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos

import androidx.room.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.Directory
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedDirectory
import kotlinx.coroutines.flow.Flow

@Dao
interface DirectoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dir: Directory)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(dir: Directory)

    @Delete()
    suspend fun delete(dir: Directory)

    @Query("SELECT COUNT(*) FROM directories")
    suspend fun testDb(): Long

    @Query(" SELECT * FROM directories ORDER BY dirname ASC")
    fun getAll(): Flow<List<TransformedDirectory>>

    @Query("SELECT * FROM directories WHERE dirname = :input")
    suspend fun findByName(input: String): List<Directory>

    @Query("SELECT * FROM directories LIMIT :n")
    suspend fun getSome(n: Int): List<Directory>

    @Query("SELECT COUNT(id) FROM songs WHERE dirid = :dirId")
    suspend fun getNoSong(dirId: Long): Int

    @Query("DELETE FROM directories")
    suspend fun deleteAll()
}