package br.com.mir4.guild.manager.model

import java.util.UUID

data class Guild(
    val id: UUID,
    val name: String,
    val countMembers: Int = 0,
    val deleted: Boolean = false
)
