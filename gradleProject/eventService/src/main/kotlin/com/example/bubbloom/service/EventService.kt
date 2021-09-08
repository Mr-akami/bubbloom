package com.example.bubbloom.service

import com.example.bubbloom.entities.Event
import org.springframework.stereotype.Service
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.stream.Collectors
import kotlin.concurrent.read
import kotlin.concurrent.write

@Service
class EventService constructor(private val repository: IEventRepository) : IEventService {

    private val repositoryLock = ReentrantReadWriteLock()

    override fun saveEvent(eventDto: EventDto): EventDto {
        var newEvent: Event?
        repositoryLock.write {
            // TODO Add ID generation logic!
            newEvent = buildEventFrom(eventDto)
            repository.save(newEvent!!)
        }
        return buildDtoFrom(newEvent!!)
    }

    override fun getEvent(id: Int): EventDto {
        var event: Event?
        repositoryLock.read {
            validateId(id)
            event = repository.get(id)
        }
        return buildDtoFrom(event!!)
    }

    override fun getAllEvents(): List<EventDto> {
        repositoryLock.read {
            return repository.getAll().stream()
                .map { event -> buildDtoFrom(event) }
                .collect(Collectors.toList())
        }
    }

    override fun updateEvent(id: Int, eventDto: EventDto) {
        val updatedEvent = buildEventFrom(eventDto)
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

    private fun buildDtoFrom(event: Event): EventDto {
        return EventDto(event.id, event.title)
    }

    private fun buildEventFrom(dto: EventDto): Event {
        return Event(dto.id, dto.title)
    }
}