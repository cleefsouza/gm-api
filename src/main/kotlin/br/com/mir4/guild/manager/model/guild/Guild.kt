package br.com.mir4.guild.manager.model.guild

import java.util.UUID

data class Guild(
    val id: UUID,
    val name: String,
    val level: Int = 1,
    val countMembers: Int = 0,
    val deleted: Boolean = false
)
