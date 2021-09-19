package com.example.bubbloom.repository

import com.example.bubbloom.entities.Event
import com.example.bubbloom.service.IEventRepository
import org.jooq.impl.DSL
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.sql.DriverManager

@Repository
class MariaDbEventRepository() : IEventRepository {

    companion object {
        private const val ADDRESS = "event-db"
        private const val PORT = "3306"
        private const val USER = "root"
        private const val PASS = "root"
        private const val DATABASE = "event_service_db"
        private const val URL = "jdbc:mariadb://$ADDRESS:$PORT/$DATABASE"

        private val EVENT_TABLE = table("event")
        private val ID_FIELD = field("id")
        private val TITLE_FIELD = field("title")
    }

    private fun connectToDB(): Connection {
        return DriverManager.getConnection(URL, USER, PASS)
    }

    override fun save(event: Event) {
        connectToDB().use { conn ->
            DSL.using(conn)
                .insertInto(EVENT_TABLE, ID_FIELD, TITLE_FIELD)
                .values(event.id, event.title)
                .execute()
        }
    }

    override fun get(id: String): Event? {
        connectToDB().use { conn ->
            return DSL.using(conn)
                .select()
                .from(EVENT_TABLE)
                .where(field("id").eq(id))
                .fetchOne()
                ?.into(Event::class.java)
        }
    }

    override fun getAll(): List<Event> {
        connectToDB().use { conn ->
            return DSL.using(conn)
                .select()
                .from(EVENT_TABLE)
                .fetch()
                .into(Event::class.java)
        }
    }

    override fun update(id: String, event: Event) {
        connectToDB().use { conn ->
            DSL.using(conn)
                .update(EVENT_TABLE)
                .set(TITLE_FIELD, event.title)
                .where(ID_FIELD.eq(event.id))
                .execute()
        }
    }

    override fun delete(id: String) {
        connectToDB().use { conn ->
            DSL.using(conn)
                .delete(EVENT_TABLE)
                .where(ID_FIELD.eq(id))
                .execute()
        }
    }
}