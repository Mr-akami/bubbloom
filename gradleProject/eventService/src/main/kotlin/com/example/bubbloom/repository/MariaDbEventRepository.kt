package com.example.bubbloom.repository

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.IEventRepository
import java.sql.DriverManager


class MariaDbEventRepository() : IEventRepository {

    init {
        DriverManager.getConnection("jdbc:mariadb://event-db/", "root", "root").use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT 'Hello World!'").use { rs ->
                    rs.first()
                    println(rs.getString(1)) //result is "Hello World!"
                }
            }
        }
    }

    @Synchronized
    override fun getAll(): List<Event> {
        return emptyList()
    }

    @Synchronized
    override fun save(event: Event) {
        return
    }
}