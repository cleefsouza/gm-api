package br.com.mir4.guild.manager.core.config

import org.jooq.Record
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service

@Service
class ConverterUtils {
    fun <T, V : Record, U : Converter<V, T>> safeConvert(converter: U, record: V): T? {
        // just making sure we *never* try to convert null results
        val hasValues = record.intoList().filterNotNull().isNotEmpty()
        return if (hasValues) converter.convert(record) else null
    }
}
