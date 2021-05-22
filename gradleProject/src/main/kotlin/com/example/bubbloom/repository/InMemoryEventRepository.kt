package com.example.bubbloom.repository

import com.example.bubbloom.service.IEventRepository
import com.example.bubbloom.domain.Event

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