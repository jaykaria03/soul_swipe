package com.discover.soulswipe.domain.usecase

import androidx.lifecycle.LiveData
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.data.model.request.OtpRequest
import com.discover.soulswipe.data.model.request.PhoneNumberRequest
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import com.discover.soulswipe.domain.repository.LoginRepository
import retrofit2.Response

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend fun postPhoneNumber(phoneNumberRequest: PhoneNumberRequest): Response<PhoneResponse> {
        return  repository.postPhoneNumber(phoneNumberRequest)
    }
    suspend fun postOTP(otpRequest: OtpRequest): Response<OtpResponse>{
        return repository.postOTP(otpRequest)
    }
}