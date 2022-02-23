package br.com.mir4.guild.manager.core.member.converter

import br.com.mir4.guild.manager.model._class._Class
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.records._ClassRecord
import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class _ClassConverter : Converter<Record, _Class> {

    override fun convert(record: Record): _Class? {
        val _classRecord = record.into(_ClassRecord::class.java)

        return _classRecord?.id?.let {
            _Class(
                id = it,
                name = _classRecord.name
            )
        }
    }
}
