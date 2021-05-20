package com.example.ItinerantConvivial.service

import com.example.ItinerantConvivial.domain.Event

interface IEventService {

    fun getAllEvents(): List<Event>

    fun saveEvent(event: Event)
}