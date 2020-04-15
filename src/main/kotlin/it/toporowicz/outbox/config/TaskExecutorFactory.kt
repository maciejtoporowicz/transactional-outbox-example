package it.toporowicz.outbox.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class TaskExecutorFactory {
    @Bean
    fun taskExecutor(
            @Value("\${taskExecutor.corePoolSize}") corePoolSize: Int,
            @Value("\${taskExecutor.maxPoolSize}") maxPoolSize: Int,
            @Value("\${taskExecutor.queueCapacity}") queueCapacity: Int
    ): TaskExecutor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
        threadPoolTaskExecutor.corePoolSize = corePoolSize
        threadPoolTaskExecutor.maxPoolSize = maxPoolSize
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity)
        return threadPoolTaskExecutor
    }
}