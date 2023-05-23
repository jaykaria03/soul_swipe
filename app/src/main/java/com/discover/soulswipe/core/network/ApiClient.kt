package com.discover.soulswipe.core.network

import com.discover.soulswipe.core.utils.Constants
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiClient {

    @POST(Constants.apiEndpointPhone)
    suspend fun postPhoneNumber(@Body request: JsonObject): Response<PhoneResponse>

    @POST(Constants.apiEndpointOtp)
    suspend fun postOTP(@Body request: JsonObject): Response<OtpResponse>

    @GET(Constants.apiEndpointNotes)
    suspend fun getNotes(@Header("Authorization") token: String): Response<NotesResponse>

}