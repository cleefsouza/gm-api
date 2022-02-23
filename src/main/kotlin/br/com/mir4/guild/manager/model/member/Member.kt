package br.com.mir4.guild.manager.model.member

import br.com.mir4.guild.manager.model._class._Class
import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.hierarchy.Hierarchy
import java.time.OffsetDateTime
import java.util.UUID

data class Member(
    val id: UUID,
    val name: String,
    val power: Double,
    val level: Int,
    var guild: Guild? = null,
    var hierarchy: Hierarchy? = null,
    var _class: _Class? = null,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    val deleted: Boolean = false
)
