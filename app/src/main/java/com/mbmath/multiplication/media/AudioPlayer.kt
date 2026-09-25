package com.mbmath.multiplication.media

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(private val context: Context) {
    private var player: MediaPlayer? = null

    fun reproducir(nombre: String) {
        player?.release()
        player = runCatching {
            MediaPlayer().apply {
                setDataSource(context.assets.openFd("audios/$nombre"))
                setOnCompletionListener { it.release() }
                prepare()
                start()
            }
        }.getOrNull()
    }

    fun liberar() {
        player?.release()
        player = null
    }
}