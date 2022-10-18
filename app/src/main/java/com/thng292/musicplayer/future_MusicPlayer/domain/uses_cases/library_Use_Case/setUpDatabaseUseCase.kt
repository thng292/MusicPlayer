package com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.library_Use_Case

import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard

class setUpDatabaseUseCase(
    val musicRepo: MusicRepoStandard
) {
    suspend fun setup() {
        musicRepo.setUpDatabase()
    }
}