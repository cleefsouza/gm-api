package br.com.mir4.guild.manager.model.member.request

import br.com.mir4.guild.manager.model._class._Class
import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.hierarchy.Hierarchy

data class MemberRequest(
    val name: String,
    val power: Double,
    val level: Int,
    val guild: Guild,
    val hierarchy: Hierarchy,
    val _class: _Class
)
