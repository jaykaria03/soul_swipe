package com.discover.soulswipe.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.map


const val USER_DATASTORE = "user_datastore"
private val Context.createDataStore:
        DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)
private val USER_TOKEN = stringPreferencesKey("USER_TOKEN")

@InstallIn(SingletonComponent::class)
@Module
object DatastorePreferences {

    suspend fun updateUserToken(context: Context, value: String = "") {
        context.createDataStore.edit {
            it[USER_TOKEN] = value
        }
    }


    fun getUserToken(context: Context) = context.createDataStore.data.map {
        it[USER_TOKEN] ?: ""
    }

}