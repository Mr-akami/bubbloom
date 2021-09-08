package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventService {

    fun saveEvent(event: EventDto): EventDto

    fun getEvent(id: Int): EventDto

    fun getAllEvents(): List<EventDto>

    fun updateEvent(id: Int, event: EventDto)

    fun deleteEvent(id: Int)
}