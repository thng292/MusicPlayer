package com.thng292.musicplayer.ui.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thng292.musicplayer.R
import com.thng292.musicplayer.future_MusicPlayer.ui.commons.MenuItem
import com.thng292.musicplayer.future_MusicPlayer.ui.theme.titleStyle

@Composable
fun menu(actionList: List<MenuItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        // NEEED TO WORK MORE
        .background(MaterialTheme.colorScheme.background)) {
        for(i in actionList) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .clickable { i.action() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = i.ico, contentDescription = null, modifier = Modifier.alpha(.5f))
                Text(text = stringResource(id = i.name), modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp), style = titleStyle
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    menu(actionList = listOf<MenuItem>(
        MenuItem(Icons.Outlined.FavoriteBorder, R.string.app_name, { }),
        MenuItem(Icons.Outlined.Favorite, R.string.app_name, { }),
        MenuItem(Icons.Outlined.Favorite, R.string.app_name, { }),
        MenuItem(Icons.Outlined.Favorite, R.string.app_name, { }),
        MenuItem(Icons.Outlined.Favorite, R.string.app_name, { }),
    ), modifier = Modifier.background(MaterialTheme.colorScheme.background))

}