package it.toporowicz.outbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class OutboxApplication

fun main(args: Array<String>) {
    runApplication<OutboxApplication>(*args)
}

