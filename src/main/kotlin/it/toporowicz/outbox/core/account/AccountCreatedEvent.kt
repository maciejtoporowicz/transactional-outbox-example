package it.toporowicz.outbox.core.account

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class AccountCreatedEvent @JsonCreator constructor(
        @JsonProperty("id") val id: String,
        @JsonProperty("email") val email: String
) : AccountEvent