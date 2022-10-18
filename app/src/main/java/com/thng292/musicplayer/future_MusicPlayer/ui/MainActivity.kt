package com.thng292.musicplayer.future_MusicPlayer.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thng292.musicplayer.future_MusicPlayer.ui.commons.MainViewModel
import com.thng292.musicplayer.ui.screens.common.LazyListDisplay
import com.thng292.musicplayer.future_MusicPlayer.ui.theme.MusicPlayerTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    //val scope = CoroutineScope(Dispatchers.Default + CoroutineName("Load Song"))
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launch(Dispatchers.Default) {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                viewModel.prepareLibrary(applicationContext)
//            }
//        }
        setContent {
            MusicPlayerTheme {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
                // Remember a SystemUiController
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                DisposableEffect(systemUiController, useDarkIcons) {
                    // Update all of the system bar colors to be transparent, and use
                    // dark icons if we're in light theme
                    systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = true)
                    // setStatusBarColor() and setNavigationBarColor() also exist
                    onDispose {}
                }

                //View Model
                val musicPlayerUiState by viewModel.uiState.collectAsState()
                runBlocking {
                    viewModel.prepareLibrary(applicationContext)
                }
                // Entry Point
                LazyListDisplay(context = LocalContext.current, songs = musicPlayerUiState.Songs)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MusicPlayerTheme {

    }
}