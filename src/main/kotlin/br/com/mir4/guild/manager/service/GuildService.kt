package br.com.mir4.guild.manager.service

import br.com.mir4.guild.manager.model.Guild
import br.com.mir4.guild.manager.repository.GuildRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GuildService(private val guildRepository: GuildRepository) {

    fun getById(guildId: UUID): Guild =
        guildRepository.findById(guildId)
            ?: throw ClassNotFoundException("Guild not found")
}
