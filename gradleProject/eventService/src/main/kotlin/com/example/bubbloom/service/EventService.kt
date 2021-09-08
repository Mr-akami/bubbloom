package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

@Service
class EventService constructor(private val repository: IEventRepository) : IEventService {

    private val repositoryLock = ReentrantReadWriteLock()

    override fun saveEvent(event: Event) {
        repositoryLock.write {
            repository.save(event)
        }
    }

    override fun getEvent(id: Int): Event {
        repositoryLock.read {
            validateId(id)
            return repository.get(id)!! // The id has been validated.
        }
    }

    override fun getAllEvents(): List<Event> {
        repositoryLock.read {
            return repository.getAll()
        }
    }

    override fun updateEvent(id: Int, event: Event) {
        repositoryLock.write {
            validateId(id)
            repository.update(id, event)
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
}