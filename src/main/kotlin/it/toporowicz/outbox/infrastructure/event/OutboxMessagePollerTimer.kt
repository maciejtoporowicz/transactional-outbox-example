package it.toporowicz.outbox.infrastructure.event;

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@Lazy(false)
class OutboxMessagePollerTimer(
        private val outboxMessagePollerFactory: OutboxMessagePollerFactory,
        private val taskExecutor: TaskExecutor ) {

    private val log = LoggerFactory.getLogger(OutboxMessagePollerTimer::class.java)

    @Scheduled(fixedRateString = "\${outboxMessages.polling.interval}")
    fun tick() {
        log.info("Polling for outbox messages")
        taskExecutor.execute(outboxMessagePollerFactory.newOutgoingEventPoller())
    }
}
