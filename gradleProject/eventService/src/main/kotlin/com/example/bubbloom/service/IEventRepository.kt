package com.example.bubbloom.service

import com.example.bubbloom.entities.Event

interface IEventRepository {

    fun save(event: Event)

    fun get(id: Int): Event?

    fun getAll(): List<Event>

    fun update(id: Int, event: Event)

    fun delete(id: Int)
}