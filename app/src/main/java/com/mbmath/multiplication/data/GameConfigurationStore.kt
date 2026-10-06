package com.mbmath.multiplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

private val Context.gameConfigurationDataStore by preferencesDataStore(name = "game_configuration")

class GameConfigurationStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.applicationContext.gameConfigurationDataStore

    suspend fun load(): GameConfiguration? {
        val preferences = dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences()) else throw exception
            }
            .first()

        val player = preferences[PLAYER] ?: return null
        return GameConfiguration(
            player = player,
            gameMode = preferences[GAME_MODE]
                ?.let { savedMode -> GameMode.entries.firstOrNull { it.name == savedMode } }
                ?: GameMode.FIND_RESULT,
            difficulty = preferences[DIFFICULTY]
                ?.let { savedDifficulty -> Difficulty.entries.firstOrNull { it.name == savedDifficulty } }
                ?: Difficulty.EASY,
            wasLoggedIn = preferences[WAS_LOGGED_IN] ?: false
        )
    }

    suspend fun save(configuration: GameConfiguration) {
        dataStore.edit { preferences ->
            preferences[PLAYER] = configuration.player
            preferences[GAME_MODE] = configuration.gameMode.name
            preferences[DIFFICULTY] = configuration.difficulty.name
            preferences[WAS_LOGGED_IN] = configuration.wasLoggedIn
        }
    }

    private companion object {
        val PLAYER = stringPreferencesKey("player")
        val GAME_MODE = stringPreferencesKey("game_mode")
        val DIFFICULTY = stringPreferencesKey("difficulty")
        val WAS_LOGGED_IN = booleanPreferencesKey("was_logged_in")
    }
}