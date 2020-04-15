package it.toporowicz.outbox.delivery.event.account

import it.toporowicz.outbox.core.account.AccountCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class WelcomeEmailTrigger {
    private val log = LoggerFactory.getLogger(WelcomeEmailTrigger::class.java)

    @EventListener
    fun onAccountCreated(event: AccountCreatedEvent) {
        log.info("Received event: $event")
    }
}