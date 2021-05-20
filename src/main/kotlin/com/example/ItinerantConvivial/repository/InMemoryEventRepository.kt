package com.example.ItinerantConvivial.repository

import com.example.ItinerantConvivial.service.IEventRepository
import com.example.ItinerantConvivial.domain.Event

class InMemoryEventRepository : IEventRepository {

    private val events = ArrayList<Event>()

    @Synchronized
    override fun getAll(): List<Event> {
        events.add(Event(1))
        return ArrayList<Event>(events)
    }

    @Synchronized
    override fun save(event: Event) {
        events.add(event)
    }
}