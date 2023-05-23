package com.discover.soulswipe.domain.usecase

import androidx.lifecycle.LiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.discover.soulswipe.domain.repository.NotesRepository
import retrofit2.Response

class NotesUseCase(
    private val repository: NotesRepository
) {
    suspend fun getNotes(): Response<NotesResponse>{
        return  repository.getNotes()
    }
}