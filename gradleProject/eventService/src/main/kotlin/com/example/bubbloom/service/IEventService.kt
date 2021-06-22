package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventService {

    fun getAllEvents(): List<Event>

    fun saveEvent(event: Event)
}