package com.discover.soulswipe.domain.repository

import androidx.lifecycle.LiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.data.model.request.OtpRequest
import com.discover.soulswipe.data.model.request.PhoneNumberRequest
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import retrofit2.Response

interface LoginRepository {

    suspend fun postPhoneNumber(phoneNumberRequest: PhoneNumberRequest): Response<PhoneResponse>
    suspend fun postOTP(otpRequest: OtpRequest):  Response<OtpResponse>

}