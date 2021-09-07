package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import org.springframework.stereotype.Service

@Service
class EventService constructor(private val repository: IEventRepository) : IEventService {

    override fun getAllEvents(): List<Event> {
        return repository.getAll()
    }

    override fun saveEvent(event: Event) {
        repository.save(event)
    }

    override fun deleteEvent(id: Int) {
        repository.delete(id)
    }
}