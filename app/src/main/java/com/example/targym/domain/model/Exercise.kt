package com.example.targym.domain.model

data class Exercise(
    val id: Long,
    val workoutDayId: Long,
    val muscleGroup: MuscleGroup,
    val name: String,
    val note: String,
    val repetitions: List<Repetition>
)