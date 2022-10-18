package com.thng292.musicplayer.future_MusicPlayer.ui.commons

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

fun f(): Unit {

}

data class MenuItem(
    val ico: ImageVector,
    @StringRes val name: Int,
    val action: () -> Unit = { },
)
