package com.thng292.musicplayer.future_MusicPlayer.domain.uses_cases.get_All_UseCase

import com.thng292.musicplayer.data.Order
import com.thng292.musicplayer.data.OrderBy
import com.thng292.musicplayer.future_MusicPlayer.domain.reposistory_standard.MusicRepoStandard
import com.thng292.musicplayer.future_MusicPlayer.domain.model.TransformedSong
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class getAllSongsUseCase(
    private val musicRepo: MusicRepoStandard
) {
    fun exec(orderBy: OrderBy = OrderBy.TITLE, order: Order = Order.ASC): Flow<List<TransformedSong>> {
        return musicRepo.getAllSongs().map { data ->
            if (order == Order.ASC) {
                when (orderBy) {
                    OrderBy.TITLE -> { data.sortedBy { it.songname } }
                    OrderBy.ARTIST -> { data.sortedBy { it.artistname } }
                    OrderBy.ALBUM -> { data.sortedBy { it.albumname } }
                    OrderBy.YEAR -> { data.sortedBy { it.year } }
                    else -> { data.sortedBy { it.length } }
                }
            } else {
                when (orderBy) {
                    OrderBy.TITLE -> { data.sortedByDescending { it.songname } }
                    OrderBy.ARTIST -> { data.sortedByDescending { it.artistname } }
                    OrderBy.ALBUM -> { data.sortedByDescending { it.albumname } }
                    OrderBy.YEAR -> { data.sortedBy { it.year } }
                    else -> { data.sortedByDescending { it.length } }
                }
            }
        }
    }
}