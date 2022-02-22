package br.com.mir4.guild.manager.controller

import br.com.mir4.guild.manager.base.WebMappings
import br.com.mir4.guild.manager.service.GuildService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(WebMappings.GUILDS)
class GuildContoller(private val guildService: GuildService) {

    @GetMapping("/{guildId}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable guildId: UUID) = guildService.getById(guildId)
}
