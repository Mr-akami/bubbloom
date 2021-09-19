package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import com.example.bubbloom.entities.IdGenerator
import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData
import org.springframework.stereotype.Service
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import kotlin.concurrent.read
import kotlin.concurrent.write

@Service
class EventService constructor(private val repository: IEventRepository, private val idGenerator: IdGenerator) : IEventService {

    private val repositoryLock = ReentrantReadWriteLock()

    override fun saveEvent(eventInput: EventInputData): EventOutputData {
        repositoryLock.write {
            val newEvent = buildEventFrom(idGenerator.generate(), eventInput)
            repository.save(newEvent)
            return buildEventOutputFrom(newEvent)
        }
    }

    override fun getEvent(id: String): EventOutputData {
        repositoryLock.read {
            validateId(id)
            val event = repository.get(id)
            return buildEventOutputFrom(event!!)
        }
    }

    override fun getAllEvents(): List<EventOutputData> {
        repositoryLock.read {
            return repository.getAll().stream()
                .map { event -> buildEventOutputFrom(event) }
                .collect(Collectors.toList())
        }
    }

    override fun updateEvent(id: String, eventInput: EventInputData) {
        val updatedEvent = buildEventFrom(id, eventInput)
        repositoryLock.write {
            validateId(id)
            repository.update(id, updatedEvent)
        }
    }

    override fun deleteEvent(id: String) {
        repositoryLock.write {
            validateId(id)
            repository.delete(id)
        }
    }

    private fun validateId(id: String) {
        repository.get(id) ?: throw IndexOutOfBoundsException("No task exists with the specified ID.")
    }

    private fun buildEventOutputFrom(event: Event): EventOutputData {
        return EventOutputData(event.id, event.title)
    }

    private fun buildEventFrom(id: String, eventInput: EventInputData): Event {
        return Event(id, eventInput.title)
    }
}