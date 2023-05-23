package com.discover.soulswipe.data.model.request

import com.google.gson.annotations.SerializedName

data class PhoneNumberRequest(
    @SerializedName("number")
    var number:String
)
