package com.example.bubbloom.service.data

import com.fasterxml.jackson.annotation.JsonProperty

data class EventInputData(
    @JsonProperty("title") val title: String
)

