package br.com.mir4.guild.manager.core.config.utils

import org.springframework.stereotype.Service
import java.time.Instant
import java.time.OffsetDateTime
import java.util.Date

@Service
class DateService {
    fun now(): Instant = Instant.now()
    fun nowDate(): Date = Date()
    fun nowODT(): OffsetDateTime = OffsetDateTime.now()
}
