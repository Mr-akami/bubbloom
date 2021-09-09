package com.example.bubbloom.service

import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData

interface IEventService {

    fun saveEvent(eventInput: EventInputData): EventOutputData

    fun getEvent(id: String): EventOutputData

    fun getAllEvents(): List<EventOutputData>

    fun updateEvent(id: String, eventInput: EventInputData)

    fun deleteEvent(id: String)
}