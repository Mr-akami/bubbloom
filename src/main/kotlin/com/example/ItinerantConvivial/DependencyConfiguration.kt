package com.example.ItinerantConvivial

import com.example.ItinerantConvivial.service.EventService
import com.example.ItinerantConvivial.service.IEventService
import com.example.ItinerantConvivial.repository.InMemoryEventRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyConfiguration {

    @Bean
    fun eventService(): IEventService {
        return EventService(InMemoryEventRepository())
    }
}