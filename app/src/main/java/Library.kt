//package com.thng292.musicplayer.data
//
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.content.ContentUris
//import android.database.Cursor
//import android.net.Uri
//import android.provider.MediaStore
//import androidx.core.app.ActivityCompat
//import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedSong
//import java.util.*
//
//class Library(val context: Context, activity: Activity) {
//    val images = mutableListOf<TransformedSong>()
//    init {
//        ActivityCompat.requestPermissions(activity,
//            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//            2000)
//        f()
//    }
//
//    fun getDaysAgo(ago: Int): String {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.DATE, ago)
//        val date: Int = (calendar.timeInMillis / 1000).toInt()
//        return date.toString()
//    }
//
//    fun f() {
//
//        // note: using MediaStore API to read shared storage is SQL-like...
//        // sql example:
//        // SELECT column1, column2 FROM table1 WHERE column2 >= 'value' ORDER BY ASC;
//        // |<------ 2------------>||<---- 1-->||<----- 3 ------------>||<---- 4 --->|
//        //      what data           from where    matching condition     how to sort
//
//        // process:
//        // prepare 4 kinds of params:
//        //  1. from where: collection uri where Media data exists
//        val targetCollectionUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//
//        //  2. what data: field names of image information
//        //     ID, NAME, DATE
//        val projection = arrayOf(
//            MediaStore.Audio.Media._ID,
//            MediaStore.Audio.Media.DURATION,
//            MediaStore.Audio.Media.TITLE,
//            MediaStore.Audio.Media.ARTIST,
//            MediaStore.Audio.Media.ALBUM,
//        )
//
////        //  3. matching conditions: to fetch
////        val selection = "${MediaStore.Audio.Media.DATE_ADDED} >= ?"
////        val selectionArg = arrayOf<String>(
////            getDaysAgo(-7)  // 1 week ago
////        )
//
//        //  4. how to sort: ASC or DESC
//        val sortOrder = "${MediaStore.Audio.Media.ALBUM} ASC"  // for getting latest photo
//
//        // fetch data using above params
//        val query: Cursor? = context.contentResolver.query(
//            targetCollectionUri,
//            projection,
//            null,
//            null,
//            sortOrder
//        )
//        query?.use { cursor ->
//            // prepare data index
//            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
//            val lengthColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
//            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
//            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
//            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
//
//            // while(selected data exist)
//            while (cursor.moveToNext()) {
//                // read field from each fetched image info
//                val id: Long = cursor.getLong(idColumn)
//                val length: Long = cursor.getLong(lengthColumn)
//                val artist: String = cursor.getString(artistColumn)
//                val album: String = cursor.getString(albumColumn)
//                val title: String = cursor.getString(titleColumn)
//
//                // very important:
//                //  Additional information 'content uri' for each media file is useful
//                //  to access shareable media files(ex: images/video/audio)
//                val contentUri = ContentUris.withAppendedId(targetCollectionUri, id)
//
//                //  add to image list
//                images.add(TransformedSong(id, title, artist, album, length.toString(), false, contentUri, null))
//            }
//        }
//    }
//}