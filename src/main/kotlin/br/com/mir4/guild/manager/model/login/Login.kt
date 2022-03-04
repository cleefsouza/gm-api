package br.com.mir4.guild.manager.model.login

import java.time.OffsetDateTime
import java.util.UUID

data class Login(
    val id: UUID,
    val username: String,
    val password: String,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    val deleted: Boolean = false
)
