package it.toporowicz.outbox.infrastructure.db;

import org.springframework.data.repository.CrudRepository

interface SpringOutboxMessageRepo : CrudRepository<OutboxMessage, Long>
