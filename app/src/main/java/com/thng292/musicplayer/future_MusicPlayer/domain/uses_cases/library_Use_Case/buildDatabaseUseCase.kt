package com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.library_Use_Case

import android.content.Context
import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard

class buildDatabaseUseCase(
    private val musicRepo: MusicRepoStandard
) {
    fun build(context: Context) {
        musicRepo.buildDatabase(context)
    }
}