package com.example.bubbloom.entities

import org.springframework.stereotype.Component
import java.util.*

@Component
class IdGenerator {

    companion object {
        private const val MAX_ID = 99999999;
    }

    private val rand = Random()

    fun generate(): String {
        return String.format("%08d", rand.nextInt(MAX_ID + 1))
    }
}