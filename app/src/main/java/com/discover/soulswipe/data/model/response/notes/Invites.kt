package com.discover.soulswipe.data.model.response.notes

data class Invites(
    val pending_invitations_count: Int,
    val profiles: List<Profile>,
    val totalPages: Int
)