package br.com.mir4.guild.manager.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GmApiApplication

fun main(args: Array<String>) {
    runApplication<GmApiApplication>(*args)
}
