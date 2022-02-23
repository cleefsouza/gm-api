package br.com.mir4.guild.manager.model._class

import java.util.UUID

/**
 * use of underscore due to the word "class"
 * being reserved by the kotlin language
 */
data class _Class(
    val id: UUID,
    val name: String
)
