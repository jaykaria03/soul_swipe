package com.discover.soulswipe.data.remote

import com.discover.soulswipe.core.network.ApiClient
import com.discover.soulswipe.core.utils.Constants
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun postPhoneNumber(phoneNumberRequest: JsonObject): Response<PhoneResponse> {
        return apiClient.postPhoneNumber(phoneNumberRequest)
    }

    suspend fun postOTP(otpRequest: JsonObject): Response<OtpResponse> {
        return apiClient.postOTP(otpRequest)
    }

    suspend fun getNotes(): Response<NotesResponse> {
        return apiClient.getNotes(Constants.token)
    }

}