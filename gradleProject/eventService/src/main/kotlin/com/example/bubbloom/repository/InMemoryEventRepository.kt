package com.example.bubbloom.repository

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.IEventRepository
import java.util.*

class InMemoryEventRepository : IEventRepository {

    private val events = HashMap<String, Event>()

    override fun save(event: Event) {
        events[event.id] = event
    }

    override fun get(id: String): Event? {
        return events[id]
    }

    override fun getAll(): List<Event> {
        return ArrayList(events.values)
    }

    override fun update(id: String, event: Event) {
        val eventToUpdate: Event? = events[id]
        eventToUpdate?.title = event.title
    }

    override fun delete(id: String) {
        events.remove(id)
    }
}