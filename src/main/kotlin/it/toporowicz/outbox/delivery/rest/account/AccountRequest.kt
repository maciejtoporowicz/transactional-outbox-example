package it.toporowicz.outbox.delivery.rest.account

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class AccountRequest @JsonCreator constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("email") val email: String
)