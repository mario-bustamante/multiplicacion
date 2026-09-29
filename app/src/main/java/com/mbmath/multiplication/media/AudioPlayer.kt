package com.mbmath.multiplication.media

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(private val context: Context) {
    private var player: MediaPlayer? = null

    fun play(name: String) {
        player?.release()
        player = runCatching {
            MediaPlayer().apply {
                setDataSource(context.assets.openFd("audios/$name"))
                setOnCompletionListener { it.release() }
                prepare()
                start()
            }
        }.getOrNull()
    }

    fun release() {
        player?.release()
        player = null
    }
}