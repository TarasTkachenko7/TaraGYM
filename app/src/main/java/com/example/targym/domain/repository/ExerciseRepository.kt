package com.example.targym.domain.repository

import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getExercisesByWorkoutDay(workoutDayId: Long): Flow<List<Exercise>>
    fun getExerciseById(exerciseId: Long): Flow<Exercise?>
    suspend fun saveExercise(exercise: Exercise)
    suspend fun deleteExercise(exerciseId: Long)
    suspend fun deleteExercisesByMuscleGroup(workoutDayId: Long, muscleGroup: MuscleGroup)
    suspend fun toggleRepetitionDone(exerciseId: Long, repetitionId: Long)
    suspend fun resetAllDoneFlags(workoutDayId: Long)
}