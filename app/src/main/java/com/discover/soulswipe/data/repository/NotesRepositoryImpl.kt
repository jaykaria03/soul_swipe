package com.discover.soulswipe.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.isNetworkAvailable
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.discover.soulswipe.data.remote.RemoteDataSource
import com.discover.soulswipe.domain.repository.NotesRepository
import retrofit2.Response
import java.io.IOException

class NotesRepositoryImpl(
    private val remote: RemoteDataSource,
    private val appContext: Application
) : NotesRepository {

    override suspend fun getNotes(): Response<NotesResponse> {
                return remote.getNotes()
    }
}