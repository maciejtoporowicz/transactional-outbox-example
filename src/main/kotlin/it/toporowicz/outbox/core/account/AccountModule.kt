package it.toporowicz.outbox.core.account

import org.springframework.stereotype.Component

@Component
class AccountModule(private val accountRepo: AccountRepo) {
    fun createNewAccount(name: String, email: String) {
        val (account, relatedEvents) = Account.newAccount(name, email)

        accountRepo.save(account, relatedEvents)
    }
}