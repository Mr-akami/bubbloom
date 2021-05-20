package com.example.ItinerantConvivial.service

import com.example.ItinerantConvivial.domain.Event

class EventService(private val repository: IEventRepository) : IEventService {

    override fun getAllEvents(): List<Event> {
        return repository.getAll()
    }

    override fun saveEvent(event: Event) {
        repository.save(event)
    }
}