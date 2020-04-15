package it.toporowicz.outbox.infrastructure.event;

import it.toporowicz.outbox.infrastructure.json.JsonMapper
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
class OutboxMessagePollerFactory(
        private val entityManagerFactory: EntityManagerFactory,
        private val appEventPublisher: ApplicationEventPublisher,
        private val jsonMapper: JsonMapper) {

    fun newOutgoingEventPoller(): OutboxMessagePoller {
        return OutboxMessagePoller(entityManagerFactory, appEventPublisher, jsonMapper)
    }
}
