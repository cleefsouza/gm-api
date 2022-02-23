package br.com.mir4.guild.manager.core.guild.service

import br.com.mir4.guild.manager.core.guild.exception.GuildInvalidInputException
import br.com.mir4.guild.manager.core.guild.exception.GuildNotFoundException
import br.com.mir4.guild.manager.core.guild.repository.GuildRepository
import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.guild.request.GuildRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GuildService(private val guildRepository: GuildRepository) {

    fun getById(guildId: UUID): Guild =
        guildRepository.findById(guildId)
            ?: throw GuildNotFoundException("Guild not found")

    @Transactional
    fun create(guildRequest: GuildRequest): Guild {
        validateFields(guildRequest)

        val guild = buildGuild(guildRequest)
        guildRepository.save(guild)

        return guild
    }

    @Transactional
    fun update(guildId: UUID, guildRequest: GuildRequest) {
        if (!guildRepository.isExists(guildId))
            throw GuildNotFoundException("Guild not found")

        validateFields(guildRequest)

        val guild = buildGuild(guildRequest, guildId)

        guildRepository.update(guild)
    }

    @Transactional
    fun delete(guildId: UUID) {
        if (!guildRepository.isExists(guildId))
            throw GuildNotFoundException("Guild not found")

        guildRepository.delete(guildId)
    }

    private fun buildGuild(guildRequest: GuildRequest, guildId: UUID? = null) = Guild(
        id = guildId ?: UUID.randomUUID(),
        name = guildRequest.name,
        level = guildRequest.level,
        countMembers = guildRequest.countMembers
    )

    private fun validateFields(guildRequest: GuildRequest?) {
        try {
            guildRequest?.let {
                with(it) {
                    require(name.isNotEmpty()) { "Name cannot be empty" }
                    require(level >= 1) { "Level cannot be lower than 1" }
                    require(countMembers >= 0) { "Count members cannot be lower than 0" }
                }
            }
        } catch (ex: IllegalArgumentException) {
            throw GuildInvalidInputException("Guild invalid input")
        }
    }
}
