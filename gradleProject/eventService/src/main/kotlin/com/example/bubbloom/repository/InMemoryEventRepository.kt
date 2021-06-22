package com.example.bubbloom.repository

import com.example.bubbloom.service.IEventRepository
import com.example.bubbloom.entities.Event

class InMemoryEventRepository : IEventRepository {

    private val events = ArrayList<Event>()

    @Synchronized
    override fun getAll(): List<Event> {
        return ArrayList<Event>(events)
    }

    @Synchronized
    override fun save(event: Event) {
        events.add(event)
    }
}