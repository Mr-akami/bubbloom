package com.example.bubbloom.repository

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.IEventRepository
import java.util.*

//@Repository
class InMemoryEventRepository : IEventRepository {

    private val events = HashMap<Int, Event>()

    @Synchronized
    override fun save(event: Event) {
        events[event.id] = event
    }

    @Override
    override fun get(id: Int): Event? {
        return events[id]
    }

    @Synchronized
    override fun getAll(): List<Event> {
        return ArrayList(events.values)
    }

    @Synchronized
    override fun update(id: Int, event: Event) {
        val eventToUpdate: Event? = events[id]
        eventToUpdate?.title = event.title
    }

    @Synchronized
    override fun delete(id: Int) {
        events.remove(id)
    }
}