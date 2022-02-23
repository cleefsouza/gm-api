package br.com.mir4.guild.manager.core.member.service

import br.com.mir4.guild.manager.core.member.exception.MemberNotFoundException
import br.com.mir4.guild.manager.core.member.repository.MemberRepository
import br.com.mir4.guild.manager.model.member.Member
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun getByIdAndGuildId(memberId: UUID, guildId: UUID): Member? =
        memberRepository.findByIdAndGuildId(memberId, guildId)
            ?: throw MemberNotFoundException("Member not found")
}
