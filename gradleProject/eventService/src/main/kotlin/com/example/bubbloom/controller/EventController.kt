package com.example.bubbloom.controller

import com.example.bubbloom.service.IEventService
import com.example.bubbloom.entities.Event
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class EventController constructor(private val eventService: IEventService) {

    @PostMapping("/events/")
    fun saveEvent(@RequestBody event: Event) {
        eventService.saveEvent(event)
    }

    @GetMapping("/events/{id}")
    fun getEvent(@PathVariable id: Int): Event? {
        try {
            return eventService.getEvent(id)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    @GetMapping("/events/")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @PutMapping("/events/{id}")
    fun updateEvent(@PathVariable id: Int, @RequestBody event: Event) {
        try {
            eventService.updateEvent(id, event)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    @DeleteMapping("/events/{id}")
    fun deleteEvent(@PathVariable id: Int) {
        try {
            eventService.deleteEvent(id)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    private fun convertException(original: Exception): ResponseStatusException {
        // TODO Create custom exceptions used by the service and convert them to appropriate HTTP errors here.
        return if (original is IndexOutOfBoundsException) {
            ResponseStatusException(HttpStatus.NOT_FOUND, original.message)
        } else {
            ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, original.message)
        }
    }
}