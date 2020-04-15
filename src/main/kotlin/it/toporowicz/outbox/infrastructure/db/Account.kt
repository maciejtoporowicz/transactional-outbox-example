package it.toporowicz.outbox.infrastructure.db

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "account")
class Account(
        @Id
        @Column(name = "id")
        private val id: String,
        @Column(name = "name")
        private val name: String,
        @Column(name = "email")
        private val email: String
)