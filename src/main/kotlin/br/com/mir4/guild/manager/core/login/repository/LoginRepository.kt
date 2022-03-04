package br.com.mir4.guild.manager.core.login.repository

import br.com.mir4.guild.manager.core.login.converter.LoginConverter
import br.com.mir4.guild.manager.model.jooq.gm_schema.tables.Login.LOGIN
import br.com.mir4.guild.manager.model.login.Login
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class LoginRepository(
    private val dsl: DSLContext,
    private val converter: LoginConverter
) {

    companion object {
        val sampleLogin = mutableListOf(
            LOGIN.ID,
            LOGIN.USERNAME,
            LOGIN.DELETED,
            LOGIN.CREATED_AT,
            LOGIN.UPDATED_AT
        )
    }

    fun findById(loginId: UUID) = dsl
        .selectFrom(LOGIN)
        .where(LOGIN.ID.eq(loginId), LOGIN.DELETED.isFalse)
        .fetchOne(this::toModel)

    fun findMyself(username: String?) = dsl
        .select(sampleLogin)
        .from(LOGIN)
        .where(LOGIN.USERNAME.eq(username), LOGIN.DELETED.isFalse)
        .fetchOneMap()

    fun findByUsername(username: String?): Login? = dsl
        .selectFrom(LOGIN)
        .where(LOGIN.USERNAME.eq(username), LOGIN.DELETED.isFalse)
        .fetchOne(this::toModel)

    fun save(login: Login) = dsl
        .insertInto(LOGIN)
        .set(LOGIN.ID, login.id)
        .set(LOGIN.USERNAME, login.username)
        .set(LOGIN.PASSWORD, login.password)
        .execute()

    private fun toModel(record: Record) = converter.convert(record)
}
