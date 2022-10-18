package com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.get_Childs_In_Parent_UseCase

import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedSong
import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class getSongsByArtistUseCase(
    private val musicRepo: MusicRepoStandard
) {
    fun exec(id: Long): Flow<List<TransformedSong>> {
        return musicRepo.getAllSongsByArtist(id).map { data->
            data.sortedBy { it.year }
        }
    }
}