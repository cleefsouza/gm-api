package br.com.mir4.guild.manager.core.login.converter

import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.records.LoginRecord
import br.com.mir4.guild.manager.model.login.Login
import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class LoginConverter : Converter<Record, Login> {

    override fun convert(record: Record): Login? {
        val userRecord = record.into(LoginRecord::class.java)

        return userRecord?.id?.let {
            Login(
                id = it,
                username = userRecord.username,
                password = userRecord.password,
                createdAt = userRecord.createdAt,
                updatedAt = userRecord.updatedAt,
                deleted = userRecord.deleted
            )
        }
    }
}
