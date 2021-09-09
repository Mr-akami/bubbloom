package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData
import org.springframework.stereotype.Service
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import kotlin.concurrent.read
import kotlin.concurrent.write

@Service
class EventService constructor(private val repository: IEventRepository) : IEventService {

    private val repositoryLock = ReentrantReadWriteLock()

    override fun saveEvent(eventInput: EventInputData): EventOutputData {
        var newEvent: Event?
        repositoryLock.write {
            val id = 0 // TODO Add ID generation logic!
            newEvent = buildEventFrom(id, eventInput)
            repository.save(newEvent!!)
        }
        return buildEventOutputFrom(newEvent!!)
    }

    override fun getEvent(id: Int): EventOutputData {
        var event: Event?
        repositoryLock.read {
            validateId(id)
            event = repository.get(id)
        }
        return buildEventOutputFrom(event!!)
    }

    override fun getAllEvents(): List<EventOutputData> {
        repositoryLock.read {
            return repository.getAll().stream()
                .map { event -> buildEventOutputFrom(event) }
                .collect(Collectors.toList())
        }
    }

    override fun updateEvent(id: Int, eventInput: EventInputData) {
        val updatedEvent = buildEventFrom(id, eventInput)
        repositoryLock.write {
            validateId(id)
            repository.update(id, updatedEvent)
        }
    }

    override fun deleteEvent(id: Int) {
        repositoryLock.write {
            validateId(id)
            repository.delete(id)
        }
    }

    private fun validateId(id: Int) {
        repository.get(id) ?: throw IndexOutOfBoundsException("No task exists with the specified ID.")
    }

    private fun buildEventOutputFrom(event: Event): EventOutputData {
        return EventOutputData(event.id, event.title)
    }

    private fun buildEventFrom(id: Int, eventInput: EventInputData): Event {
        return Event(id, eventInput.title)
    }
}