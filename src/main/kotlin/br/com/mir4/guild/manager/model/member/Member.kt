package br.com.mir4.guild.manager.model.member

import br.com.mir4.guild.manager.model._class._Class
import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.hierarchy.Hierarchy
import java.time.Instant
import java.util.UUID

data class Member(
    val id: UUID,
    val name: String,
    val power: Double,
    val level: Int,
    val guild: Guild,
    val hierarchy: Hierarchy,
    val _class: _Class,
    val createdAt: Instant = Instant.now(),
    val updateAt: Instant = Instant.now(),
    val deleted: Boolean = false
)
