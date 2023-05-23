package com.discover.soulswipe.presentation.notes.viewmodel;

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.isNetworkAvailable
import com.discover.soulswipe.data.model.request.PhoneNumberRequest
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.discover.soulswipe.domain.usecase.NotesUseCase
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class NotesViewModel @Inject
constructor(
    private val notesUseCase: NotesUseCase,
    private val context: Application
) : ViewModel() {


    private var _notesResponse: MutableLiveData<NetworkResult<NotesResponse>> = MutableLiveData()
    var notesResponse: LiveData<NetworkResult<NotesResponse>> = _notesResponse

    init {
        getNotes()
    }
    fun getNotes() {
        viewModelScope.launch {
            _notesResponse.value  = (NetworkResult.Loading())
            try {
                if (isNetworkAvailable(context )) {
                    _notesResponse.value  = (NetworkResult.Success(notesUseCase.getNotes().body()))
                }else{
                    _notesResponse.value  = (
                            NetworkResult.Error(
                                "Please check your internet connection"
                            )
                            )
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        _notesResponse.value  = (
                                NetworkResult.Error(
                                    "Please check your internet connection"
                                )
                                )
                    }

                    else -> {
                        _notesResponse.value  = (
                                NetworkResult.Error(
                                    throwable.message ?: "Something went wrong"
                                )
                                )
                    }
                }
            }
        }
    }

}
