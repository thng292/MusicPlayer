package com.thng292.musicplayer.util

fun milisecToMinString(duration: Long): String {
    val min = (duration/1000/60).toInt().toString()
    val sec = ((duration/1000) % 60).toInt().toString()
    return min+":"+sec
}