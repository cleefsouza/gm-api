package br.com.mir4.guild.manager.model.guild.request

data class GuildRequest(
    val name: String,
    val level: Int = 1,
    val countMembers: Int = 0
)
