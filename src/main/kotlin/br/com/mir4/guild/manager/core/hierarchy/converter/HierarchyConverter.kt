package br.com.mir4.guild.manager.core.hierarchy.converter

import br.com.mir4.guild.manager.model.hierarchy.Hierarchy
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.records.HierarchyRecord
import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class HierarchyConverter : Converter<Record, Hierarchy> {

    override fun convert(record: Record): Hierarchy? {
        val hierarchyRecord = record.into(HierarchyRecord::class.java)

        return hierarchyRecord?.id?.let {
            Hierarchy(
                id = it,
                name = hierarchyRecord.name
            )
        }
    }
}
