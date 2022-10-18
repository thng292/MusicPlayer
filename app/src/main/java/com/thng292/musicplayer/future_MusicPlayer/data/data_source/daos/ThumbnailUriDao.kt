package com.thng292.musicplayer.future_MusicPlayer.data.data_source.daos

import androidx.room.*
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.ThumbnailUri
import kotlinx.coroutines.flow.Flow

@Dao
interface ThumbnailUriDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(thumbnailUri: ThumbnailUri)

    @Query("SELECT * FROM thumbnailsuri")
    fun getAll(): Flow<List<ThumbnailUri>>

    @Delete
    suspend fun delete(thumbnailUri: ThumbnailUri)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(thumbnailUri: ThumbnailUri)

    @Query("DELETE FROM thumbnailsuri")
    suspend fun deleteAll()
}