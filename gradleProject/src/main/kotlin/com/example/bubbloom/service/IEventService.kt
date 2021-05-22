package com.example.bubbloom.service

import com.example.bubbloom.domain.Event

interface IEventService {

    fun getAllEvents(): List<Event>

    fun saveEvent(event: Event)
}