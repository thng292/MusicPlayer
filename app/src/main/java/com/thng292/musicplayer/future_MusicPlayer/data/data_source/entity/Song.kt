package com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity
import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.room.*

@Entity(tableName = "songs",
//    foreignKeys = [
//    ForeignKey(
//        entity = Album::class,
//        parentColumns = ["id"],
//        childColumns = ["albumid"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE
//    ),
//    ForeignKey(
//        entity = Artist::class,
//        parentColumns = ["id"],
//        childColumns = ["artistid"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE
//    ),ForeignKey(
//        entity = Genre::class,
//        parentColumns = ["id"],
//        childColumns = ["genreid"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE
//    ),ForeignKey(
//        entity = Directory::class,
//        parentColumns = ["id"],
//        childColumns = ["dirid"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE
//    ),
//],
    indices = [
        Index("songname", unique = false),
        Index("artistid", unique = false),
        Index("albumid", unique = false),
        Index("genreid", unique = false),
        Index("year", unique = false),
        Index("dirid", unique = false)
    ]
)
@Immutable
data class Song(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "songname") val name: String,
    @ColumnInfo(name = "artistid") val artistId: Long,
    @ColumnInfo(name = "albumid") val albumId: Long,
    @ColumnInfo(name = "genreid") val genreId: Long,
    @ColumnInfo(name = "dirid") val dirId: Long,
    @ColumnInfo(name = "track") val track: Int,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "length") val lengthInSec: Long,
    @ColumnInfo(name = "count") val count: Long,
    @ColumnInfo(name = "isfavoritesong") val isfavorite: Boolean,
    @ColumnInfo(name = "source") val source: String,
)

