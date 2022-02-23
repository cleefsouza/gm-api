package br.com.mir4.guild.manager.core.member.service

import br.com.mir4.guild.manager.core._class.repository._ClassRepository
import br.com.mir4.guild.manager.core.guild.exception.GuildNotFoundException
import br.com.mir4.guild.manager.core.guild.repository.GuildRepository
import br.com.mir4.guild.manager.core.hierarchy.repository.HierarchyRepository
import br.com.mir4.guild.manager.core.member.exception.MemberInvalidInputException
import br.com.mir4.guild.manager.core.member.exception.MemberNotFoundException
import br.com.mir4.guild.manager.core.member.repository.MemberRepository
import br.com.mir4.guild.manager.model.member.Member
import br.com.mir4.guild.manager.model.member.request.MemberRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val guildRepository: GuildRepository,
    private val hierarchyRepository: HierarchyRepository,
    private val _classRespository: _ClassRepository
) {

    fun getByIdAndGuildId(memberId: UUID, guildId: UUID): Member? =
        memberRepository.findByIdAndGuildId(memberId, guildId)
            ?: throw MemberNotFoundException("Member not found")

    fun getAllByGuildId(guildId: UUID): List<Member> {
        if (!guildRepository.isExists(guildId))
            throw MemberNotFoundException("Member not found")

        return memberRepository.findAllByGuildId(guildId)
    }

    @Transactional
    fun create(guildId: UUID, memberRequest: MemberRequest): Member {
        if (!guildRepository.isExists(guildId))
            throw GuildNotFoundException("Guild not found")

        validateFields(memberRequest)
        val member = buildMember(memberRequest, guildId)

        memberRepository.save(member)

        return member
    }

    @Transactional
    fun update(memberId: UUID, guildId: UUID, memberRequest: MemberRequest) {
        if (!memberRepository.isExists(memberId, guildId))
            throw MemberNotFoundException("Member not found")

        validateFields(memberRequest)
        val member = buildMember(memberRequest, guildId, memberId)

        memberRepository.update(member)
    }

    @Transactional
    fun delete(memberId: UUID, guildId: UUID) {
        if (!memberRepository.isExists(memberId, guildId))
            throw MemberNotFoundException("Member not found")

        memberRepository.delete(memberId)
    }

    private fun buildMember(
        memberRequest: MemberRequest,
        guildId: UUID,
        memberId: UUID? = null
    ) = Member(
        id = memberId ?: UUID.randomUUID(),
        name = memberRequest.name,
        level = memberRequest.level,
        power = memberRequest.power,
        guild = guildRepository.findById(guildId),
        hierarchy = hierarchyRepository.findById(memberRequest.hierarchyId),
        _class = _classRespository.findById(memberRequest._classId)
    )

    private fun validateFields(memberRequest: MemberRequest?) {
        try {
            memberRequest?.let {
                with(it) {
                    require(name.isNotEmpty()) { "Name cannot be empty" }
                    require(hierarchyId.toString().isNotEmpty()) { "Hierarchy cannot be empty" }
                    require(_classId.toString().isNotEmpty()) { "Class cannot be empty" }
                    require(1 <= power) { "Power cannot be lower than 1" }
                    require(1 <= level) { "Level cannot be lower than 1" }
                }
            }
        } catch (ex: IllegalArgumentException) {
            throw MemberInvalidInputException("Member invalid input")
        }
    }
}
