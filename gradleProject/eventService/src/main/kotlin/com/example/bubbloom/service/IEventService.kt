package com.example.bubbloom.service

import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData

interface IEventService {

    fun saveEvent(eventInput: EventInputData): EventOutputData

    fun getEvent(id: Int): EventOutputData

    fun getAllEvents(): List<EventOutputData>

    fun updateEvent(id: Int, eventInput: EventInputData)

    fun deleteEvent(id: Int)
}