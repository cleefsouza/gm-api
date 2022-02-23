package br.com.mir4.guild.manager.core.member.controller

import br.com.mir4.guild.manager.core.config.WebMappings
import br.com.mir4.guild.manager.core.member.service.MemberService
import br.com.mir4.guild.manager.model.member.request.MemberRequest
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
@RequestMapping(WebMappings.MEMBERS)
class MemberController(private val memberService: MemberService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(@PathVariable guildId: UUID) = memberService.getAllByGuildId(guildId)

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable guildId: UUID, @PathVariable memberId: UUID) =
        memberService.getByIdAndGuildId(memberId, guildId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @PathVariable guildId: UUID,
        @RequestBody memberRequest: MemberRequest
    ) = memberService.create(guildId, memberRequest)

    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(
        @PathVariable guildId: UUID,
        @PathVariable memberId: UUID,
        @RequestBody memberRequest: MemberRequest
    ) = memberService.update(memberId, guildId, memberRequest)

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable guildId: UUID, @PathVariable memberId: UUID) = memberService.delete(memberId, guildId)
}
