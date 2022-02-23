package br.com.mir4.guild.manager.core.hierarchy.repository

import br.com.mir4.guild.manager.core.hierarchy.converter.HierarchyConverter
import br.com.mir4.guild.manager.model.hierarchy.Hierarchy
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Hierarchy.HIERARCHY
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class HierarchyRepository(
    private val dsl: DSLContext,
    private val converter: HierarchyConverter
) {
    fun findById(hierarchyId: UUID): Hierarchy? =
        dsl.selectFrom(HIERARCHY).where(HIERARCHY.ID.eq(hierarchyId)).fetchOne(this::toModel)

    private fun toModel(record: Record) = converter.convert(record)
}
