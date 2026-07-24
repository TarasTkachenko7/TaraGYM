package com.example.targym.data.impl

import com.example.targym.data.Storage
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ExerciseRepositoryImpl : ExerciseRepository {
    private val _exercises = MutableStateFlow<List<Exercise>>(Storage.mockExercises)

    override fun getExercisesByWorkoutDay(workoutDayId: Long): Flow<List<Exercise>> {
        return _exercises.map { allExercises ->
            allExercises.filter { it.workoutDayId == workoutDayId }
        }
    }

    override fun getExerciseById(exerciseId: Long): Flow<Exercise?> {
        return _exercises.map { allExercises ->
            allExercises.find { it.id == exerciseId }
        }
    }

    override suspend fun saveExercise(exercise: Exercise) {
        _exercises.update { allExercises ->
            val exists = allExercises.any { it.id == exercise.id }
            if (exists) {
                allExercises.map { if (it.id == exercise.id) exercise else it }
            } else {
                val newId = System.currentTimeMillis()
                val newExercise = exercise.copy(
                    id = newId,
                    repetitions = exercise.repetitions.map { it.copy(exerciseId = newId) }
                )
                allExercises + newExercise
            }
        }
    }

    override suspend fun deleteExercise(exerciseId: Long) {
        _exercises.update { allExercises -> allExercises.filter { it.id != exerciseId } }
    }

    override suspend fun deleteExercisesByMuscleGroup(workoutDayId: Long, muscleGroup: MuscleGroup) {
        _exercises.update { allExercises ->
            allExercises.filterNot { it.workoutDayId == workoutDayId && it.muscleGroup == muscleGroup }
        }
    }

    override suspend fun toggleRepetitionDone(exerciseId: Long, repetitionId: Long) {
        _exercises.update { allExercises ->
            allExercises.map { exercise ->
                if (exercise.id == exerciseId) {
                    exercise.copy(
                        repetitions = exercise.repetitions.map { rep ->
                            if (rep.id == repetitionId) rep.copy(isDone = !rep.isDone) else rep
                        }
                    )
                } else exercise
            }
        }
    }

    override suspend fun resetAllDoneFlags(workoutDayId: Long) {
        _exercises.update { allExercises ->
            allExercises.map { exercise ->
                if (exercise.workoutDayId == workoutDayId) {
                    exercise.copy(repetitions = exercise.repetitions.map { it.copy(isDone = false) })
                } else exercise
            }
        }
    }
}