package com.discover.soulswipe.data.model.response.notes

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
)