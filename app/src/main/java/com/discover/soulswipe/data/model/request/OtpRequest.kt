package com.discover.soulswipe.data.model.request

import com.google.gson.annotations.SerializedName

data class OtpRequest(
    @SerializedName("number")
    var number:String,
    @SerializedName("otp")
    var otp:String
)
