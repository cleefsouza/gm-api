package br.com.mir4.guild.manager.core.config.utils

class WebMappings {
    companion object {
        const val BASE = "/mir4/api"
        const val LOGIN = "$BASE/login"
        const val GUILDS = "$BASE/guilds"
        const val MEMBERS = "$BASE/guilds/{guildId}/members"
    }
}
