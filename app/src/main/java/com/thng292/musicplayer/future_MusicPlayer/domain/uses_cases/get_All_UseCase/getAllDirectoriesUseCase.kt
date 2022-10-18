package com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.get_All_UseCase

import com.thng292.musicplayer.data.Order
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedDirectory
import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class getAllDirectoriesUseCase(
    private val musicRepo: MusicRepoStandard
) {
    fun exec(order: Order = Order.ASC): Flow<List<TransformedDirectory>> {
        return musicRepo.getAllDir().map { data->
            if (order == Order.ASC) data.sortedBy { it.dirname }
            else data.sortedByDescending { it.dirname }
        }
    }
}