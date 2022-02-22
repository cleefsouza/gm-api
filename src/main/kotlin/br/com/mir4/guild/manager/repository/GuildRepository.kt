package br.com.mir4.guild.manager.repository

import br.com.mir4.guild.manager.converter.GuildConverter
import br.com.mir4.guild.manager.model.Guild
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Guild.GUILD
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class GuildRepository(
    private val dsl: DSLContext,
    private val converter: GuildConverter
) {
    fun findById(guildId: UUID): Guild? = dsl
        .selectFrom(GUILD)
        .where(GUILD.ID.eq(guildId), GUILD.DELETED.isFalse)
        .fetchOne(this::toModel)

    private fun toModel(record: Record) = converter.convert(record)
}
