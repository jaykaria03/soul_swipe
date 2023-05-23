package com.discover.soulswipe.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.isNetworkAvailable
import com.discover.soulswipe.data.model.request.OtpRequest
import com.discover.soulswipe.data.model.request.PhoneNumberRequest
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import com.discover.soulswipe.data.remote.RemoteDataSource
import com.discover.soulswipe.domain.repository.LoginRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response
import java.io.IOException

class LoginRepositoryImpl(
    private val remote: RemoteDataSource,
    private val appContext: Application
) : LoginRepository {

    override suspend fun postPhoneNumber(phoneNumberRequest: PhoneNumberRequest): Response<PhoneResponse> {
                val gson = Gson()
                val phoneRequestObject: JsonObject = gson.toJsonTree(phoneNumberRequest).asJsonObject
                return remote.postPhoneNumber(phoneRequestObject)
    }

    override suspend fun postOTP(otpRequest: OtpRequest):  Response<OtpResponse> {
                val gson = Gson()
                val otpRequestObject: JsonObject = gson.toJsonTree(otpRequest).asJsonObject
                return remote.postOTP(otpRequestObject)
    }

}