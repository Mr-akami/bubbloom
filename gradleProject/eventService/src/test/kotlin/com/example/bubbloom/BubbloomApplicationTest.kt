package com.example.bubbloom

import com.example.bubbloom.service.data.EventInputData
import com.example.bubbloom.service.data.EventOutputData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(EventTestConfiguration::class)
internal class BubbloomApplicationTest(@Autowired val mockMvc: MockMvc) {

    val objectMapper = ObjectMapper()

    @Test
    fun `Test a sequence of basic CRUD operations`() {

        // Create an event
        val postOutput1: EventOutputData = postEvent(EventInputData("title1"))
        assertEquals("title1", postOutput1.title)

        // Create another event
        val postOutput2: EventOutputData = postEvent(EventInputData("title1"))
        assertEquals("title1", postOutput2.title)

        // POST with an illegal body
        val illegalInput = "{\"illegalKey\":\"value\"}"
        mockMvc.perform(
            MockMvcRequestBuilders.post("/events/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(illegalInput)
        )
            .andExpect(status().is4xxClientError)

        // Get all events
        val events = getAllEvents()
        assertEquals(2, events.size)

        // Get each event
        assertNotNull(getEvent(events[0].id))
        assertNotNull(getEvent(events[1].id))
        getEventAndFail("/events/illegalId)")

        // Update event
        val updateId = events[0].id
        updateEvent(updateId, EventInputData("new title1"))
        assertEquals("new title1", getEvent(updateId).title)
        updateEventAndFail("illegalId", EventInputData("illegal"))

        // Delete events
        deleteEventAndFail("illegalId")
        assertEquals(2, getAllEvents().size)
        deleteEvent(events[0].id)
        assertEquals(1, getAllEvents().size)
        deleteEvent(events[1].id)
        assertEquals(0, getAllEvents().size)
    }

    fun postEvent(input: EventInputData): EventOutputData {
        val body: String = mockMvc.perform(
            MockMvcRequestBuilders.post("/events/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input))
        )
            .andExpect(status().isCreated)
            .andReturn().response.contentAsString
        return objectMapper.readValue(body)
    }

    fun getAllEvents(): List<EventOutputData> {
        val body: String = mockMvc.perform(MockMvcRequestBuilders.get("/events/"))
            .andExpect(status().isOk)
            .andReturn().response.contentAsString
        return objectMapper.readValue(body)
    }

    fun getEvent(id: String): EventOutputData {
        val body: String = mockMvc.perform(MockMvcRequestBuilders.get("/events/$id"))
            .andExpect(status().isOk)
            .andReturn().response.contentAsString
        return objectMapper.readValue(body)
    }

    fun getEventAndFail(id: String) {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/$id"))
            .andExpect(status().isNotFound)
    }

    fun updateEvent(id: String, input: EventInputData) {
        updateEvent(id, input, status().isOk)
    }

    fun updateEventAndFail(id: String, input: EventInputData) {
        updateEvent(id, input, status().isNotFound)
    }

    fun updateEvent(id: String, input: EventInputData, matcher: ResultMatcher) {
        mockMvc.perform(
            MockMvcRequestBuilders.put("/events/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input))
        )
            .andExpect(matcher)
    }

    fun deleteEvent(id: String) {
        deleteEvent(id, status().isOk)
    }

    fun deleteEventAndFail(id: String) {
        deleteEvent(id, status().isNotFound)
    }

    fun deleteEvent(id: String, matcher: ResultMatcher) {
        mockMvc.perform(MockMvcRequestBuilders.delete("/events/$id"))
            .andExpect(matcher)
    }
}