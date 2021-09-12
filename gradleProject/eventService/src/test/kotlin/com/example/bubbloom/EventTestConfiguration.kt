package com.example.bubbloom

import com.example.bubbloom.repository.InMemoryEventRepository
import com.example.bubbloom.service.IEventRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class EventTestConfiguration {

    @Bean
    @Primary
    fun getTestRepository(): IEventRepository {
        return InMemoryEventRepository()
    }
}