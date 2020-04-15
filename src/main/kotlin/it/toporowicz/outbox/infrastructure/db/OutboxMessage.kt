package it.toporowicz.outbox.infrastructure.db;

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "outbox_message")
class OutboxMessage(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "id")
        val id: Long? = null,
        @Column(name = "type")
        val eventType: String,
        @Column(name = "json_content")
        val jsonContent: String
)
