package it.toporowicz.outbox.adapter

import it.toporowicz.outbox.infrastructure.json.JsonMapper
import it.toporowicz.outbox.core.account.Account
import it.toporowicz.outbox.core.account.AccountEvent
import it.toporowicz.outbox.core.account.AccountRepo
import it.toporowicz.outbox.infrastructure.db.OutboxMessage
import it.toporowicz.outbox.infrastructure.db.SpringAccountRepo
import it.toporowicz.outbox.infrastructure.db.SpringOutboxMessageRepo
import org.springframework.stereotype.Component
import javax.transaction.Transactional
import kotlin.streams.toList

@Component
class AccountRepoAdapter(
        private val accountRepo: SpringAccountRepo,
        private val outboxMessageRepo: SpringOutboxMessageRepo,
        private val jsonMapper: JsonMapper) : AccountRepo {

    @Transactional
    override fun save(account: Account, relatedEvents: List<AccountEvent>) {
        accountRepo.save(asEntity(account))
        outboxMessageRepo.saveAll(relatedEvents.stream().map { asEntity(it) }.toList())
    }

    private fun asEntity(account: Account): it.toporowicz.outbox.infrastructure.db.Account {
        return it.toporowicz.outbox.infrastructure.db.Account(account.id, account.name, account.email)
    }

    private fun asEntity(event: AccountEvent): OutboxMessage {
        return OutboxMessage(
                eventType = event.javaClass.canonicalName,
                jsonContent = jsonMapper.toJson(event)
        )
    }
}