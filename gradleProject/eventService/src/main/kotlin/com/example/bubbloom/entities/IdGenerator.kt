package com.example.bubbloom.entities

import org.springframework.stereotype.Component
import java.util.*

@Component
class IdGenerator {

    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}