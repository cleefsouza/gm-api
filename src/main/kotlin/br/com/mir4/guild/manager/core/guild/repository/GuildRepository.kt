package br.com.mir4.guild.manager.core.guild.repository

import br.com.mir4.guild.manager.core.config.DateService
import br.com.mir4.guild.manager.core.guild.converter.GuildConverter
import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Guild.GUILD
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class GuildRepository(
    private val dsl: DSLContext,
    private val converter: GuildConverter,
    private val dateService: DateService
) {
    fun isExists(guildId: UUID): Boolean =
        dsl.fetchExists(GUILD, GUILD.ID.eq(guildId), GUILD.DELETED.isFalse)

    fun findById(guildId: UUID): Guild? = dsl
        .selectFrom(GUILD)
        .where(GUILD.ID.eq(guildId), GUILD.DELETED.isFalse)
        .fetchOne(this::toModel)

    fun save(guild: Guild) = dsl
        .insertInto(GUILD)
        .set(GUILD.ID, guild.id)
        .set(GUILD.NAME, guild.name)
        .set(GUILD.LEVEL, guild.level)
        .set(GUILD.SERVER, guild.server)
        .set(GUILD.COUNT_MEMBERS, guild.countMembers)
        .execute()

    fun update(guild: Guild) = dsl
        .update(GUILD)
        .set(GUILD.NAME, guild.name)
        .set(GUILD.COUNT_MEMBERS, guild.countMembers)
        .set(GUILD.SERVER, guild.server)
        .set(GUILD.UPDATED_AT, dateService.nowODT())
        .where(GUILD.ID.eq(guild.id), GUILD.DELETED.isFalse)
        .execute()

    fun delete(guildId: UUID) = dsl
        .update(GUILD)
        .set(GUILD.DELETED, true)
        .set(GUILD.UPDATED_AT, dateService.nowODT())
        .where(GUILD.ID.eq(guildId))
        .execute()

    private fun toModel(record: Record) = converter.convert(record)
}
