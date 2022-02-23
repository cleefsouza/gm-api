package br.com.mir4.guild.manager.core._class.repository

import br.com.mir4.guild.manager.core._class.converter._ClassConverter
import br.com.mir4.guild.manager.model._class._Class
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables._Class._CLASS
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class _ClassRepository(
    private val dsl: DSLContext,
    private val converter: _ClassConverter
) {
    fun findById(_classId: UUID): _Class? =
        dsl.selectFrom(_CLASS).where(_CLASS.ID.eq(_classId)).fetchOne(this::toModel)

    private fun toModel(record: Record) = converter.convert(record)
}
