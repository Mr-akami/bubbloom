package com.example.bubbloom.service.data

import com.fasterxml.jackson.annotation.JsonProperty

data class EventOutputData(
    @JsonProperty("id") val id: String,
    @JsonProperty("title") val title: String
)