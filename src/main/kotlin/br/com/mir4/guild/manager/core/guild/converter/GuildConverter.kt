package br.com.mir4.guild.manager.core.guild.converter

import br.com.mir4.guild.manager.model.guild.Guild
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.records.GuildRecord
import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class GuildConverter : Converter<Record, Guild> {

    override fun convert(record: Record): Guild? {
        val guildRecord = record.into(GuildRecord::class.java)

        return guildRecord?.id?.let {
            Guild(
                id = it,
                name = guildRecord.name,
                level = guildRecord.level,
                countMembers = guildRecord.countMembers,
                server = guildRecord.server,
                createdAt = guildRecord.createdAt,
                updatedAt = guildRecord.updatedAt,
                deleted = guildRecord.deleted
            )
        }
    }
}
