package br.com.mir4.guild.manager.model.member.request

import java.util.UUID

data class MemberRequest(
    val name: String,
    val power: Double,
    val level: Int,
    val hierarchyId: UUID,
    val _classId: UUID
)
