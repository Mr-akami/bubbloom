package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventRepository {

    fun save(event: Event)

    fun get(id: String): Event?

    fun getAll(): List<Event>

    fun update(id: String, event: Event)

    fun delete(id: String)
}