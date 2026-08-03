package com.example.targym.data.util

import java.util.concurrent.atomic.AtomicLong

object IdGenerator {
    private val counter = AtomicLong(System.currentTimeMillis() * 1000)
    fun generateId(): Long {
        return counter.incrementAndGet()
    }
}