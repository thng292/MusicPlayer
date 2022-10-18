package com.thng292.musicplayer.future_MusicPlayer.ui.commons

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thng292.musicplayer.future_MusicPlayer.data.data_source.entity.*
import com.thng292.musicplayer.future_MusicPlayer.data.repsistory.MusicRepository
import com.thng292.musicplayer.future_MusicPlayer.domain.model.*
import com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.get_All_UseCase.*
import com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.library_Use_Case.buildDatabaseUseCase
import com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.library_Use_Case.setUpDatabaseUseCase
import kotlinx.coroutines.flow.*

enum class Category {
    Songs, Albums, Artists, Folders, Genres
}

data class MusicPlayerUiState(
    val Songs: List<TransformedSong> = listOf(),
    val Albums: List<TransformedAlbum> = listOf(),
    val Artists: List<TransformedArtist> = listOf(),
    val Dirs: List<TransformedDirectory> = listOf(),
    val Genres: List<TransformedGenre> = listOf(),
    val Categ: Category = Category.Songs,
)

//private val _uiState = MutableStateFlow(GameUiState())
//val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MusicPlayerUiState())
    val uiState: StateFlow<MusicPlayerUiState> = _uiState.asStateFlow()

    private val musicRepository = MusicRepository()

    suspend fun prepareLibrary(context: Context) {
        buildDatabaseUseCase(musicRepository).build(context)
        setUpDatabaseUseCase(musicRepository).setup()
        _uiState.update { currState->
            currState.copy(
                Songs = getAllSongsUseCase(musicRepository).exec().last()
            )
        }
        _uiState.update { currState->
            currState.copy(
                Albums = getAllAlbumsUseCase(musicRepository).exec().last()
            )
        }
        _uiState.update { currState->
            currState.copy(
                Artists = getAllArtistsUseCase(musicRepository).exec().last()
            )
        }
        _uiState.update { currState->
            currState.copy(
                Dirs = getAllDirectoriesUseCase(musicRepository).exec().last()
            )
        }
        _uiState.update { currState->
            currState.copy(
                Genres = getAllGenresUseCase(musicRepository).exec().last()
            )
        }
    }
}