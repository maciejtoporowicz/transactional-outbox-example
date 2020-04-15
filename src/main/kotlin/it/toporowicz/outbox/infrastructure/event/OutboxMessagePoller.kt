package it.toporowicz.outbox.infrastructure.event;

import it.toporowicz.outbox.infrastructure.json.JsonMapper
import it.toporowicz.outbox.infrastructure.db.OutboxMessage
import org.springframework.context.ApplicationEventPublisher
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

class OutboxMessagePoller(
        private val entityManagerFactory: EntityManagerFactory,
        private val applicationEventPublisher: ApplicationEventPublisher,
        private val jsonMapper: JsonMapper
) : Runnable {

    @Override
    override fun run() {
        val entityManager = entityManagerFactory.createEntityManager()
        try {
            val transaction = entityManager.transaction
            transaction.begin()

            val message = deleteFirstMessage(entityManager)

            if (message == null) {
                transaction.rollback()
                return
            }

            try {
                val deserializedContent = deserializeContentFrom(message)
                applicationEventPublisher.publishEvent(deserializedContent)
                transaction.commit()
            } catch (e: Throwable) {
                try {
                    transaction.rollback()
                } catch (rollbackException: Throwable) {
                    e.addSuppressed(rollbackException)
                }
                throw e
            }
        } finally {
            entityManager.close()
        }
    }

    private fun deserializeContentFrom(event: OutboxMessage): Any {
        return jsonMapper.fromJson(event.jsonContent, Class.forName(event.eventType))
    }

    private fun deleteFirstMessage(entityManager: EntityManager): OutboxMessage? {
        val messages = entityManager
                .createNativeQuery("""
                    DELETE FROM outbox_message 
                        WHERE id = (   
                            SELECT id FROM outbox_message   
                            ORDER BY id   
                            FOR UPDATE SKIP LOCKED   
                            LIMIT 1 
                        ) 
                    RETURNING id, type, json_content;""".trimIndent(),
                    OutboxMessage::class.java
                ).resultList

        if (messages.size == 1) {
            return messages[0] as OutboxMessage;
        }

        if (messages.isEmpty()) {
            return null;
        }

        throw IllegalStateException("More than one outgoing event found - this is a bug.");
    }
}
