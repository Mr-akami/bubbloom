package com.example.bubbloom.controller

import com.example.bubbloom.service.IEventService
import com.example.bubbloom.entities.Event
import org.springframework.web.bind.annotation.*

@RestController
class EventController constructor(private val eventService: IEventService) {

    @PostMapping("/events/")
    fun saveEvent(@RequestBody event: Event) {
        eventService.saveEvent(event)
    }

    @GetMapping("/events/{id}")
    fun getEvent(@PathVariable id: Int): Event? {
        return eventService.getEvent(id)
    }

    @GetMapping("/events/")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @PutMapping("/events/{id}")
    fun updateEvent(@PathVariable id: Int, @RequestBody event: Event) {
        eventService.updateEvent(id, event)
    }

    @DeleteMapping("/events/{id}")
    fun deleteEvent(@PathVariable id: Int) {
        eventService.deleteEvent(id)
    }
}