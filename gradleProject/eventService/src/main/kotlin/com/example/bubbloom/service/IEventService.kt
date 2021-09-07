package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventService {

    fun saveEvent(event: Event)

    fun getEvent(id: Int): Event?

    fun getAllEvents(): List<Event>

    fun updateEvent(id: Int, event: Event)

    fun deleteEvent(id: Int)
}