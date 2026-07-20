package com.example.targym.data

import com.example.targym.domain.model.Advice
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.domain.model.WorkoutDay

object Storage {

    val mockDays = mutableListOf<WorkoutDay>(
        WorkoutDay(id = 1, name = "Верх А"),
        WorkoutDay(id = 2, name = "Низ А"),
        WorkoutDay(id = 3, name = "Верх Б")
    )

    val mockExercises = mutableListOf<Exercise>(
        // ==================== ДЕНЬ 1: ВЕРХ А (workoutDayId = 1) ====================
        Exercise(
            id = 1,
            workoutDayId = 1,
            name = "Подтягивания",
            note = "Сведя лопатки",
            muscleGroup = MuscleGroup.BACK,
            repetitions = listOf(
                Repetition(id = 101, weight = 0.0, quantity = 10, isDone = false, exerciseId = 1),
                Repetition(id = 102, weight = 0.0, quantity = 8, isDone = false, exerciseId = 1),
                Repetition(id = 103, weight = 5.0, quantity = 6, isDone = false, exerciseId = 1)
            )
        ),
        Exercise(
            id = 2,
            workoutDayId = 1,
            name = "Тяга верхнего блока",
            note = "К груди",
            muscleGroup = MuscleGroup.BACK,
            repetitions = listOf(
                Repetition(id = 104, weight = 45.0, quantity = 12, isDone = false, exerciseId = 2),
                Repetition(id = 105, weight = 50.0, quantity = 10, isDone = false, exerciseId = 2)
            )
        ),
        Exercise(
            id = 3,
            workoutDayId = 1,
            name = "Жим гантелей лежа",
            note = "Угол 30 градусов",
            muscleGroup = MuscleGroup.CHEST,
            repetitions = listOf(
                Repetition(id = 106, weight = 22.0, quantity = 10, isDone = false, exerciseId = 3),
                Repetition(id = 107, weight = 24.0, quantity = 8, isDone = false, exerciseId = 3)
            )
        ),
        Exercise(
            id = 4,
            workoutDayId = 1,
            name = "Сгибания на бицепс",
            note = "С супинацией",
            muscleGroup = MuscleGroup.BICEPS,
            repetitions = listOf(
                Repetition(id = 108, weight = 12.0, quantity = 12, isDone = false, exerciseId = 4),
                Repetition(id = 109, weight = 14.0, quantity = 10, isDone = false, exerciseId = 4)
            )
        ),

        // ==================== ДЕНЬ 2: НИЗ А (workoutDayId = 2) ====================
        Exercise(
            id = 5,
            workoutDayId = 2,
            name = "Приседания со штангой",
            note = "До параллели",
            muscleGroup = MuscleGroup.LEGS,
            repetitions = listOf(
                Repetition(id = 201, weight = 80.0, quantity = 10, isDone = false, exerciseId = 5),
                Repetition(id = 202, weight = 90.0, quantity = 8, isDone = false, exerciseId = 5)
            )
        ),
        Exercise(
            id = 6,
            workoutDayId = 2,
            name = "Румынская тяга",
            note = "Чуть согнув колени",
            muscleGroup = MuscleGroup.LEGS,
            repetitions = listOf(
                Repetition(id = 203, weight = 60.0, quantity = 12, isDone = false, exerciseId = 6),
                Repetition(id = 204, weight = 70.0, quantity = 10, isDone = false, exerciseId = 6)
            )
        ),

        // ==================== ДЕНЬ 3: ВЕРХ Б (workoutDayId = 3) ====================
        Exercise(
            id = 7,
            workoutDayId = 3,
            name = "Жим штанги стоя",
            note = "Армейский жим",
            muscleGroup = MuscleGroup.SHOULDERS,
            repetitions = listOf(
                Repetition(id = 301, weight = 40.0, quantity = 10, isDone = false, exerciseId = 7),
                Repetition(id = 302, weight = 45.0, quantity = 8, isDone = false, exerciseId = 7)
            )
        )
    )

    val mockAdvice = mutableListOf<Advice>(
        Advice(id = 1, exerciseId = 1, title = "Ручками к себе"),
        Advice(id = 2, exerciseId = 2, title = "Не раскачивай корпус локтями вперед")
    )
}