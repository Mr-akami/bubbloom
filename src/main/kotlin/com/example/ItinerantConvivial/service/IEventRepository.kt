package com.example.ItinerantConvivial.service

import com.example.ItinerantConvivial.domain.Event

interface IEventRepository {

    fun getAll(): List<Event>

    fun save(event: Event)
}