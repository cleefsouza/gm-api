package br.com.mir4.guild.manager.core.member.controller

import br.com.mir4.guild.manager.core.config.WebMappings
import br.com.mir4.guild.manager.core.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(WebMappings.MEMBERS)
class MemberController(private val memberService: MemberService) {

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable guildId: UUID, @PathVariable memberId: UUID) =
        memberService.getByIdAndGuildId(memberId, guildId)
}
