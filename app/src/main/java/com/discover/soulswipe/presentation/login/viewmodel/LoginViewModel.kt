package com.discover.soulswipe.presentation.login.viewmodel;

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.isNetworkAvailable
import com.discover.soulswipe.data.model.request.OtpRequest
import com.discover.soulswipe.data.model.request.PhoneNumberRequest
import com.discover.soulswipe.data.model.response.OtpResponse
import com.discover.soulswipe.data.model.response.PhoneResponse
import com.discover.soulswipe.domain.usecase.LoginUseCase
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@HiltViewModel
class LoginViewModel @Inject
constructor(
    private val loginUseCase: LoginUseCase,
    private val context: Application
) : ViewModel() {


    private var _phoneResponse: MutableLiveData<NetworkResult<PhoneResponse>> = MutableLiveData()
    var phoneResponse: LiveData<NetworkResult<PhoneResponse>> = _phoneResponse

    private var _otpResponse: MutableLiveData<NetworkResult<OtpResponse>> = MutableLiveData()
    var otpResponse: LiveData<NetworkResult<OtpResponse>> = _otpResponse



    fun postPhoneNumber(phoneNumber:String) {
        viewModelScope.launch {

            _phoneResponse.value = (NetworkResult.Loading())
            try {
                if (isNetworkAvailable(context)) {
                    _phoneResponse.value = NetworkResult.Success((loginUseCase.postPhoneNumber(PhoneNumberRequest(phoneNumber)).body()))
                }else{
                    _phoneResponse.value = (
                            NetworkResult.Error(
                                "Please check your internet connection"
                            )
                            )
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        _phoneResponse.value = (
                                NetworkResult.Error(
                                    "Please check your internet connection"
                                )
                                )
                    }

                    else -> {
                        _phoneResponse.value = (
                                NetworkResult.Error(
                                    throwable.message ?: "Something went wrong"
                                )
                                )
                    }
                }
            }
        }
    }

    fun postOtp(phoneNumber:String,otp:String) {
        viewModelScope.launch {

            _otpResponse.value  = (NetworkResult.Loading())
            try {
                if (isNetworkAvailable(context)) {
                    _otpResponse.value = NetworkResult.Success((loginUseCase.postOTP(OtpRequest(phoneNumber,otp))).body())
                }else{
                    _phoneResponse.value = (
                            NetworkResult.Error(
                                "Please check your internet connection"
                            )
                            )
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        _otpResponse.value  = (
                                NetworkResult.Error(
                                    "Please check your internet connection"
                                )
                                )
                    }

                    else -> {
                        _otpResponse.value  = (
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
