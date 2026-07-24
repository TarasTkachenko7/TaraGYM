package com.example.targym.data.util

import java.util.UUID

object IdGenerator {
    fun generateId(): Long {
        return UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
    }
}