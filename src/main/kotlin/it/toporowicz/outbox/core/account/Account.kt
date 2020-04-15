package it.toporowicz.outbox.core.account

import java.util.*

class Account private constructor(val id: String, val name: String, val email: String) {
    companion object {
        fun newAccount(name: String, email: String): Pair<Account, List<AccountEvent>> {
            val account = Account(UUID.randomUUID().toString(), name, email)
            val relatedEvents = listOf(AccountCreatedEvent(account.id, email))

            return account to relatedEvents
        }
    }
}
