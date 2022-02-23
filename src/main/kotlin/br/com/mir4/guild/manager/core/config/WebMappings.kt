package br.com.mir4.guild.manager.core.config

class WebMappings {
    companion object {
        const val BASE = "/mir4/api"
        const val GUILDS = "$BASE/guilds"
        const val MEMBERS = "$BASE/guilds/{guildId}/members"
    }
}
