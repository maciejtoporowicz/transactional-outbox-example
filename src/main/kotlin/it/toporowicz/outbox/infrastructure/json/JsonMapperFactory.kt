package it.toporowicz.outbox.infrastructure.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonMapperFactory {
    @Bean
    fun jsonMapper(objectMapper: ObjectMapper): JsonMapper {
        return JsonMapper(objectMapper)
    }
}

class JsonMapper(private val objectMapper: ObjectMapper) {
    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return objectMapper.readValue(json, clazz)
    }

    fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }
}