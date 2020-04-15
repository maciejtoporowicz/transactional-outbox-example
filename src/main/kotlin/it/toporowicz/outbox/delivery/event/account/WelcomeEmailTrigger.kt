package it.toporowicz.outbox.delivery.event.account

import it.toporowicz.outbox.core.account.AccountCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class WelcomeEmailTrigger {
    @EventListener
    fun onAccountCreated(event: AccountCreatedEvent) {
        println("Sending email according to $event")
    }
}