package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class EventService constructor(private val repository: IEventRepository) : IEventService {

    override fun saveEvent(event: Event) {
        repository.save(event)
    }

    override fun getEvent(id: Int): Event? {
        validateId(id)
        return repository.get(id)
    }

    override fun getAllEvents(): List<Event> {
        return repository.getAll()
    }

    override fun updateEvent(id: Int, event: Event) {
        validateId(id)
        repository.update(id, event)
    }

    override fun deleteEvent(id: Int) {
        validateId(id)
        repository.delete(id)
    }

    private fun validateId(id: Int) {
        repository.get(id) ?: throw IllegalArgumentException("No task exists with the specified ID.")
    }
}