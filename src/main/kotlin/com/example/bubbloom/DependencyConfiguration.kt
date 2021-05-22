package com.example.bubbloom

import com.example.bubbloom.service.EventService
import com.example.bubbloom.service.IEventService
import com.example.bubbloom.repository.InMemoryEventRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyConfiguration {

    @Bean
    fun eventService(): IEventService {
        return EventService(InMemoryEventRepository())
    }
}