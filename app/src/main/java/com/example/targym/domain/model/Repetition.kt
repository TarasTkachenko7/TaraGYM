package com.example.targym.domain.model

data class Repetition(
    val id: Long,
    val exerciseId: Long,
    val weight: Double,
    val quantity: Int,
    val isDone: Boolean
)