package com.example.ItinerantConvivial.controller

import com.example.ItinerantConvivial.service.IEventService
import com.example.ItinerantConvivial.domain.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController @Autowired constructor(private val eventService: IEventService) {

    @GetMapping("/events/")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @PostMapping("/events/")
    fun saveEvent(@RequestBody event: Event) {
        eventService.saveEvent(event)
    }
}