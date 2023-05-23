package com.discover.soulswipe.domain.repository

import androidx.lifecycle.LiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import retrofit2.Response

interface NotesRepository {

    suspend fun getNotes(): Response<NotesResponse>

}