package br.com.mir4.guild.manager.model

import br.com.mir4.guild.manager.model.enum.HierarchyEnum
import java.util.UUID

data class Member(
    val id: UUID,
    val name: String,
    val power: Double,
    val level: Int,
    val hierarchy: String = HierarchyEnum.MEMBER.name
)
