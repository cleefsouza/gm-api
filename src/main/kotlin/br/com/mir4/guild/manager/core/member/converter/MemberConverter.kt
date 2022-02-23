package br.com.mir4.guild.manager.core.member.converter

import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.records.MemberRecord
import br.com.mir4.guild.manager.model.member.Member
import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class MemberConverter : Converter<Record, Member> {

    override fun convert(record: Record): Member? {
        val memberRecord = record.into(MemberRecord::class.java)

        return memberRecord?.id?.let {
            Member(
                id = it,
                name = memberRecord.name,
                power = memberRecord.power,
                level = memberRecord.level,
                createdAt = memberRecord.createdAt,
                updatedAt = memberRecord.updatedAt,
                deleted = memberRecord.deleted
            )
        }
    }
}
