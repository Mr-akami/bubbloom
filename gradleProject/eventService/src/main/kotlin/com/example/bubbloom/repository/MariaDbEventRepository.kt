package com.example.bubbloom.repository

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.IEventRepository
import org.springframework.stereotype.Repository
import java.sql.DriverManager
import java.sql.ResultSet
import kotlin.collections.ArrayList

@Repository
class MariaDbEventRepository() : IEventRepository {

    companion object {
        private const val ADDRESS = "event-db"
        private const val PORT = "3306"
        private const val USER = "root"
        private const val PASS = "root"
        private const val DATABASE = "event_service_db"
        private const val TABLE = "event"
        private const val URL = "jdbc:mariadb://$ADDRESS:$PORT/$DATABASE"
        private const val INSERT_QUERY = "INSERT INTO $TABLE (id, title) VALUES (?, ?);"
        private const val GET_QUERY = "SELECT * FROM $TABLE WHERE id=?;"
        private const val GET_ALL_QUERY = "SELECT * FROM $TABLE;"
        private const val UPDATE_QUERY = "UPDATE $TABLE SET title = ? WHERE id = ?;"
        private const val DELETE_QUERY = "DELETE FROM $TABLE WHERE id=?;"
    }

    override fun save(event: Event) {
        DriverManager.getConnection(URL, USER, PASS).use { conn ->
            conn.prepareStatement(INSERT_QUERY).use { statement ->
                statement.setString(1, event.id)
                statement.setString(2, event.title)
                statement.executeUpdate()
            }
        }
    }

    override fun get(id: String): Event? {
        DriverManager.getConnection(URL, USER, PASS).use { conn ->
            conn.prepareStatement(GET_QUERY).use { statement ->
                statement.setString(1, id)
                statement.executeQuery().use { resultSet ->
                    return if (resultSet.next()) buildEventFrom(resultSet) else null
                }
            }
        }
    }

    override fun getAll(): List<Event> {
        val events = ArrayList<Event>()
        DriverManager.getConnection(URL, USER, PASS).use { conn ->
            conn.createStatement().use { statement ->
                statement.executeQuery(GET_ALL_QUERY).use { resultSet ->
                    while (resultSet.next()) {
                        events.add(buildEventFrom(resultSet))
                    }
                }
            }
        }
        return events
    }

    override fun update(id: String, event: Event) {
        DriverManager.getConnection(URL, USER, PASS).use { conn ->
            conn.prepareStatement(UPDATE_QUERY).use { statement ->
                statement.setString(1, event.title)
                statement.setString(2, id)
                statement.executeUpdate()
            }
        }
    }

    override fun delete(id: String) {
        DriverManager.getConnection(URL, USER, PASS).use { conn ->
            conn.prepareStatement(DELETE_QUERY).use { statement ->
                statement.setString(1, id)
                statement.executeUpdate()
            }
        }
    }

    private fun buildEventFrom(resultSet: ResultSet): Event {
        val id = resultSet.getString("id")
        val title = resultSet.getString("title")
        return Event(id, title)
    }
}