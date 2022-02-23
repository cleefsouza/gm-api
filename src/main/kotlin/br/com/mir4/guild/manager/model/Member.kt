package br.com.mir4.guild.manager.model

import br.com.mir4.guild.manager.model.guild.Guild
import java.util.UUID

data class Member(
    val id: UUID,
    val name: String,
    val power: Double,
    val level: Int,
    val guild: Guild,
    val hierarchy: Hierarchy,
    val _class: _Class,
    val deleted: Boolean = false
)
