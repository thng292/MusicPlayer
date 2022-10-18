package com.thng292.musicplayer.ui.screens.common

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.thng292.musicplayer.R
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedSong
import com.thng292.musicplayer.future_MusicPlayer.ui.commons.MenuItem
import com.thng292.musicplayer.future_MusicPlayer.ui.theme.subTitleStyle
import com.thng292.musicplayer.future_MusicPlayer.ui.theme.titleStyle

@Composable
fun LazyListDisplay(context: Context, songs: List<TransformedSong>, menuItems: List<MenuItem> = listOf(), modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(songs.size, key = { songs[it].id }) { i ->
            CardSong(
                context,
                songs[i].songname,
                songs[i].artistname,
                songs[i].thumbnailuri,
                menuItems,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Composable
fun LazyListDisplayWithBg(context: Context, songs: List<TransformedSong>, menuItems: List<MenuItem> = listOf(), modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(songs.size, key = { songs[it].id }){
            i -> CardSong(context, songs[i].songname, songs[i].artistname, songs[i].thumbnailuri, menuItems, modifier = Modifier.background(MaterialTheme.colorScheme.background))
        }
    }
}

@Composable
fun CardSong(context: Context, title: String, subTitle: String, thumbnail: String, btns: List<MenuItem>, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .fillMaxWidth()
        .padding(5.dp)
        .height(60.dp)
        .clickable {

        }) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            //Image(bitmap = BitmapFactory.decodeFile(thumbnail).asImageBitmap(), contentDescription = null, contentScale = ContentScale.Inside, modifier = Modifier.size(40.dp)) Need to be worked on
            Image(imageVector = Icons.Outlined.PlayArrow, contentDescription = null, contentScale = ContentScale.Inside, modifier = Modifier.size(50.dp))
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Text(text = title, style = titleStyle, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth(.8f))
                Text(text = subTitle , style = subTitleStyle, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth(.8f))
            }
        }
        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = stringResource(id = R.string.more), modifier = Modifier.clickable {

        })
    }
}