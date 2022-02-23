package br.com.mir4.guild.manager.core.member.repository

import br.com.mir4.guild.manager.core._class.converter._ClassConverter
import br.com.mir4.guild.manager.core.config.ConverterUtils
import br.com.mir4.guild.manager.core.config.DateService
import br.com.mir4.guild.manager.core.guild.converter.GuildConverter
import br.com.mir4.guild.manager.core.hierarchy.converter.HierarchyConverter
import br.com.mir4.guild.manager.core.member.converter.MemberConverter
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Guild.GUILD
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Hierarchy.HIERARCHY
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Member.MEMBER
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables._Class._CLASS
import br.com.mir4.guild.manager.model.member.Member
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MemberRepository(
    private val dsl: DSLContext,
    private val dateService: DateService,
    private val converter: MemberConverter,
    private val guildConverter: GuildConverter,
    private val _classConverter: _ClassConverter,
    private val hierarchyConverter: HierarchyConverter,
    private val converterUtils: ConverterUtils
) {
    fun isExists(memberId: UUID, guildId: UUID): Boolean =
        dsl.fetchExists(MEMBER, MEMBER.ID.eq(memberId), MEMBER.GUILD_ID.eq(guildId), MEMBER.DELETED.isFalse)

    fun findByIdAndGuildId(memberId: UUID, guildId: UUID): Member? = dsl
        .select()
        .from(MEMBER)
        .join(GUILD)
        .on(GUILD.ID.eq(MEMBER.GUILD_ID))
        .join(HIERARCHY)
        .on(HIERARCHY.ID.eq(MEMBER.HIERARCHY_ID))
        .join(_CLASS)
        .on(_CLASS.ID.eq(MEMBER.CLASS_ID))
        .where(MEMBER.ID.eq(memberId), MEMBER.GUILD_ID.eq(guildId), MEMBER.DELETED.isFalse)
        .orderBy(MEMBER.LEVEL)
        .fetchOne(this::toModel)

    fun findAllByGuildId(guildId: UUID): List<Member> = dsl
        .select()
        .from(MEMBER)
        .join(GUILD)
        .on(GUILD.ID.eq(MEMBER.GUILD_ID))
        .join(HIERARCHY)
        .on(HIERARCHY.ID.eq(MEMBER.HIERARCHY_ID))
        .join(_CLASS)
        .on(_CLASS.ID.eq(MEMBER.CLASS_ID))
        .where(MEMBER.GUILD_ID.eq(guildId), MEMBER.DELETED.isFalse)
        .orderBy(MEMBER.LEVEL.desc())
        .fetch(this::toModel)

    fun save(member: Member) = dsl
        .insertInto(MEMBER)
        .set(MEMBER.ID, member.id)
        .set(MEMBER.NAME, member.name)
        .set(MEMBER.POWER, member.power)
        .set(MEMBER.LEVEL, member.level)
        .set(MEMBER.GUILD_ID, member.guild?.id)
        .set(MEMBER.HIERARCHY_ID, member.hierarchy?.id)
        .set(MEMBER.CLASS_ID, member._class?.id)
        .execute()

    fun update(member: Member) = dsl
        .update(MEMBER)
        .set(MEMBER.NAME, member.name)
        .set(MEMBER.POWER, member.power)
        .set(MEMBER.LEVEL, member.level)
        .set(MEMBER.GUILD_ID, member.guild?.id)
        .set(MEMBER.HIERARCHY_ID, member.hierarchy?.id)
        .set(MEMBER.CLASS_ID, member._class?.id)
        .set(MEMBER.UPDATED_AT, dateService.nowODT())
        .where(MEMBER.ID.eq(member.id), MEMBER.GUILD_ID.eq(member.guild?.id))
        .execute()

    fun delete(memberId: UUID) = dsl
        .update(MEMBER)
        .set(MEMBER.DELETED, true)
        .set(MEMBER.UPDATED_AT, dateService.nowODT())
        .where(MEMBER.ID.eq(memberId))
        .execute()

    private fun toModel(record: Record): Member? {
        val member = converter.convert(record)

        val guild = converterUtils.safeConvert(guildConverter, record)
        val hierarchy = converterUtils.safeConvert(hierarchyConverter, record)
        val _class = converterUtils.safeConvert(_classConverter, record)

        member?.let {
            it.guild = guild
            it.hierarchy = hierarchy
            it._class = _class
        }

        return member
    }
}
