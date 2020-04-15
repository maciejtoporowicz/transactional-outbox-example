package it.toporowicz.outbox.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.sql.DataSource

@Configuration
class DbConfig {
    @Bean(destroyMethod = "close")
    fun dataSource(env: Environment): DataSource? {
        val dataSourceConfig = HikariConfig()
        dataSourceConfig.driverClassName = env.getRequiredProperty("db.driver")
        dataSourceConfig.jdbcUrl = env.getRequiredProperty("db.url")
        dataSourceConfig.username = env.getRequiredProperty("db.username")
        dataSourceConfig.password = env.getRequiredProperty("db.password")
        return HikariDataSource(dataSourceConfig)
    }
}