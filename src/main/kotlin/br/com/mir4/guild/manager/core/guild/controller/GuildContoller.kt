package br.com.mir4.guild.manager.core.guild.controller

import br.com.mir4.guild.manager.core.config.utils.WebMappings
import br.com.mir4.guild.manager.core.guild.service.GuildService
import br.com.mir4.guild.manager.model.guild.request.GuildRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody guildRequest: GuildRequest) = guildService.create(guildRequest)

    @PatchMapping("/{guildId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable guildId: UUID, @RequestBody guildRequest: GuildRequest) =
        guildService.update(guildId, guildRequest)

    @DeleteMapping("/{guildId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable guildId: UUID) = guildService.delete(guildId)
}
