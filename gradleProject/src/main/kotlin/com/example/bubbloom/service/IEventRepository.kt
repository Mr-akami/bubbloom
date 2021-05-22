package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventRepository {

    fun getAll(): List<Event>

    fun save(event: Event)
}