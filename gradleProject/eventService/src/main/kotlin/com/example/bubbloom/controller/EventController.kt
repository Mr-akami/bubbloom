package com.example.bubbloom.controller

import com.example.bubbloom.service.IEventService
import com.example.bubbloom.entities.Event
import org.springframework.web.bind.annotation.*

@RestController
class EventController constructor(private val eventService: IEventService) {

    @GetMapping("/events/")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @PostMapping("/events/")
    fun saveEvent(@RequestBody event: Event) {
        eventService.saveEvent(event)
    }

    @DeleteMapping("/events/{id}")
    fun deleteEvent(@PathVariable id: Int) {
        eventService.deleteEvent(id)
    }
}