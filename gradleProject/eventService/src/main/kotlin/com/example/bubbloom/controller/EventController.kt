package com.example.bubbloom.controller

import com.example.bubbloom.service.IEventService
import com.example.bubbloom.entities.Event
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalArgumentException

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
        } catch (e: IllegalArgumentException) {
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
        } catch (e: IllegalArgumentException) {
            throw convertException(e)
        }
    }

    @DeleteMapping("/events/{id}")
    fun deleteEvent(@PathVariable id: Int) {
        try {
            eventService.deleteEvent(id)
        } catch (e: IllegalArgumentException) {
            throw convertException(e)
        }
    }

    private fun convertException(original: Exception): ResponseStatusException {
        return if (original is IllegalArgumentException) {
            ResponseStatusException(HttpStatus.NOT_FOUND, original.message)
        } else {
            ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, original.message)
        }
    }
}