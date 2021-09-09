package com.example.bubbloom.controller

import com.example.bubbloom.service.IEventService
import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class EventController constructor(private val eventService: IEventService) {

    @PostMapping("/events/")
    fun saveEvent(@RequestBody eventInput: EventInputData): EventOutputData {
        return eventService.saveEvent(eventInput)
    }

    @GetMapping("/events/{id}")
    fun getEvent(@PathVariable id: String): EventOutputData {
        try {
            return eventService.getEvent(id)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    @GetMapping("/events/")
    fun getAllEvents(): List<EventOutputData> {
        return eventService.getAllEvents()
    }

    @PutMapping("/events/{id}")
    fun updateEvent(@PathVariable id: String, @RequestBody eventInput: EventInputData) {
        try {
            eventService.updateEvent(id, eventInput)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    @DeleteMapping("/events/{id}")
    fun deleteEvent(@PathVariable id: String) {
        try {
            eventService.deleteEvent(id)
        } catch (e: Exception) {
            throw convertException(e)
        }
    }

    private fun convertException(original: Exception): ResponseStatusException {
        // Convert exceptions thrown by the service to appropriate HTTP errors here.
        return if (original is IndexOutOfBoundsException) {
            ResponseStatusException(HttpStatus.NOT_FOUND, original.message)
        } else {
            ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, original.message)
        }
    }
}