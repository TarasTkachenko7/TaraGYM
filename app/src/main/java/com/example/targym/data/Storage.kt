package com.example.targym.data

import com.example.targym.domain.model.Advice
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.domain.model.WorkoutDay

object Storage {

    val mockDays = mutableListOf<WorkoutDay>(
        WorkoutDay(id = 1, name = "Верх А"),
        WorkoutDay(id = 2, name = "Верх Б"),
        WorkoutDay(id = 3, name = "Ножной"),
        WorkoutDay(id = 4, name = "Пресс"),
    )

    val mockExercises = mutableListOf<Exercise>(
        // ==================== ДЕНЬ 1: ВЕРХ А (workoutDayId = 1) ==========
        Exercise(
            id = 1,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.BACK,
            name = "Верхняя тяга",
            repetitions = mutableListOf(
                Repetition(id = 1, exerciseId = 1, weight = 68.0, quantity = 12, isDone = false),
                Repetition(id = 2, exerciseId = 1, weight = 72.0, quantity = 10, isDone = false),
                Repetition(id = 3, exerciseId = 1, weight = 72.0, quantity = 9, isDone = false)
            ),
            note = "Последний идеально"
        ),
        Exercise(
            id = 2,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.BACK,
            name = "Горизонт",
            repetitions = mutableListOf(
                Repetition(id = 4, exerciseId = 2, weight = 63.0, quantity = 12, isDone = false),
                Repetition(id = 5, exerciseId = 2, weight = 72.0, quantity = 10, isDone = false)
            ),
            note = "Последний максимум"
        ),
        Exercise(
            id = 3,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.BACK,
            name = "Рычаги",
            repetitions = mutableListOf(
                Repetition(id = 6, exerciseId = 3, weight = 25.0, quantity = 12, isDone = false),
                Repetition(id = 7, exerciseId = 3, weight = 30.0, quantity = 8, isDone = false)
            ),
            note = "на каждой, жёстко"
        ),
        Exercise(
            id = 4,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.CHEST,
            name = "Жим Наклонная",
            repetitions = mutableListOf(
                Repetition(id = 8, exerciseId = 4, weight = 28.0, quantity = 12, isDone = false),
                Repetition(id = 9, exerciseId = 4, weight = 28.0, quantity = 11, isDone = false),
                Repetition(id = 10, exerciseId = 4, weight = 30.0, quantity = 10, isDone = false)
            ),
            note = "с 28, второй 30, третий 32. выложись"
        ),
        Exercise(
            id = 5,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.CHEST,
            name = "Бабочка",
            repetitions = mutableListOf(
                Repetition(id = 11, exerciseId = 5, weight = 130.0, quantity = 13, isDone = false),
                Repetition(id = 12, exerciseId = 5, weight = 140.0, quantity = 11, isDone = false)
            ),
            note = "сделай идеально бро"
        ),
        Exercise(
            id = 6,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.TRICEPS,
            name = "Трицепс Блок",
            repetitions = mutableListOf(
                Repetition(id = 13, exerciseId = 6, weight = 27.0, quantity = 12, isDone = false)
            ),
            note = "с 27(12) жёстко"
        ),
        Exercise(
            id = 7,
            workoutDayId = 1,
            muscleGroup = MuscleGroup.BICEPS,
            name = "Бицепс Супинация",
            repetitions = mutableListOf(
                Repetition(id = 14, exerciseId = 7, weight = 20.0, quantity = 24, isDone = false),
                Repetition(id = 15, exerciseId = 7, weight = 22.0, quantity = 18, isDone = false),
                Repetition(id = 16, exerciseId = 7, weight = 22.0, quantity = 14, isDone = false)
            ),
            note = "чистейше"
        ),

        // ==================== ДЕНЬ 2: ВЕРХ Б (workoutDayId = 2) ==========
        Exercise(
            id = 8,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.CHEST,
            name = "Жим",
            repetitions = mutableListOf(
                Repetition(id = 17, exerciseId = 8, weight = 80.0, quantity = 11, isDone = false),
                Repetition(id = 18, exerciseId = 8, weight = 82.5, quantity = 9, isDone = false),
                Repetition(id = 19, exerciseId = 8, weight = 85.0, quantity = 7, isDone = false)
            ),
            note = "прожимай как суку"
        ),
        Exercise(
            id = 9,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.CHEST,
            name = "Жим Наклонная",
            repetitions = mutableListOf(
                Repetition(id = 20, exerciseId = 9, weight = 30.0, quantity = 10, isDone = false),
                Repetition(id = 21, exerciseId = 9, weight = 32.0, quantity = 8, isDone = false)
            ),
            note = "жёстко включайся"
        ),
        Exercise(
            id = 10,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.CHEST,
            name = "Бабочка",
            repetitions = mutableListOf(
                Repetition(id = 22, exerciseId = 10, weight = 35.0, quantity = 13, isDone = false),
                Repetition(id = 23, exerciseId = 10, weight = 40.0, quantity = 10, isDone = false)
            ),
            note = "грудью! 1 сверху, 1 снизу видны"
        ),
        Exercise(
            id = 11,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.BACK,
            name = "Верхняя тяга",
            repetitions = mutableListOf(
                Repetition(id = 24, exerciseId = 11, weight = 68.0, quantity = 12, isDone = false),
                Repetition(id = 25, exerciseId = 11, weight = 68.0, quantity = 10, isDone = false),
                Repetition(id = 26, exerciseId = 11, weight = 72.0, quantity = 8, isDone = false)
            ),
            note = null
        ),
        Exercise(
            id = 12,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.BACK,
            name = "Горизонт",
            repetitions = mutableListOf(
                Repetition(id = 27, exerciseId = 12, weight = 63.0, quantity = 12, isDone = false),
                Repetition(id = 28, exerciseId = 12, weight = 68.0, quantity = 10, isDone = false)
            ),
            note = null
        ),
        Exercise(
            id = 13,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.TRICEPS,
            name = "Трицепс Сзади",
            repetitions = mutableListOf(
                Repetition(id = 29, exerciseId = 13, weight = 33.0, quantity = 14, isDone = false),
                Repetition(id = 30, exerciseId = 13, weight = 35.0, quantity = 12, isDone = false),
                Repetition(id = 31, exerciseId = 13, weight = 37.0, quantity = 10, isDone = false)
            ),
            note = "тяжело, сделай"
        ),
        Exercise(
            id = 14,
            workoutDayId = 2,
            muscleGroup = MuscleGroup.BICEPS,
            name = "Бицепс Молоточки",
            repetitions = mutableListOf(
                Repetition(id = 32, exerciseId = 14, weight = 20.0, quantity = 24, isDone = false),
                Repetition(id = 33, exerciseId = 14, weight = 22.0, quantity = 18, isDone = false),
                Repetition(id = 34, exerciseId = 14, weight = 22.0, quantity = 16, isDone = false)
            ),
            note = "чистейше. выложись последний"
        ),

        // ==================== ДЕНЬ 3: НОЖНОЙ (workoutDayId = 3) ==========
        Exercise(
            id = 15,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Жим ногами",
            repetitions = mutableListOf(
                Repetition(id = 35, exerciseId = 15, weight = 170.0, quantity = 10, isDone = false),
                Repetition(id = 36, exerciseId = 15, weight = 180.0, quantity = 8, isDone = false),
                Repetition(id = 37, exerciseId = 15, weight = 190.0, quantity = 6, isDone = false)
            ),
            note = "включайся, должно быть тяжело"
        ),
        Exercise(
            id = 16,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Выпады",
            repetitions = mutableListOf(
                Repetition(id = 38, exerciseId = 16, weight = 30.0, quantity = 11, isDone = false),
                Repetition(id = 39, exerciseId = 16, weight = 35.0, quantity = 9, isDone = false),
                Repetition(id = 40, exerciseId = 16, weight = 40.0, quantity = 8, isDone = false)
            ),
            note = null
        ),
        Exercise(
            id = 17,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Румынская Тяга",
            repetitions = mutableListOf(
                Repetition(id = 41, exerciseId = 17, weight = 42.5, quantity = 12, isDone = false),
                Repetition(id = 42, exerciseId = 17, weight = 45.0, quantity = 10, isDone = false),
                Repetition(id = 43, exerciseId = 17, weight = 47.5, quantity = 8, isDone = false)
            ),
            note = "с 45(10) жёстко выложись"
        ),
        Exercise(
            id = 18,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Сгибания",
            repetitions = mutableListOf(
                Repetition(id = 44, exerciseId = 18, weight = 25.0, quantity = 12, isDone = false)
            ),
            note = "3 снизу видно тяжело сделай"
        ),
        Exercise(
            id = 19,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Разгибания",
            repetitions = mutableListOf(
                Repetition(id = 45, exerciseId = 19, weight = 55.0, quantity = 12, isDone = false),
                Repetition(id = 46, exerciseId = 19, weight = 60.0, quantity = 10, isDone = false),
                Repetition(id = 47, exerciseId = 19, weight = 65.0, quantity = 8, isDone = false)
            ),
            note = "с 60(12) жёстко"
        ),
        Exercise(
            id = 20,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.LEGS,
            name = "Икры",
            repetitions = mutableListOf(
                Repetition(id = 48, exerciseId = 20, weight = 50.0, quantity = 13, isDone = false),
                Repetition(id = 49, exerciseId = 20, weight = 55.0, quantity = 11, isDone = false),
                Repetition(id = 50, exerciseId = 20, weight = 60.0, quantity = 9, isDone = false)
            ),
            note = "тяжело"
        ),
        Exercise(
            id = 21,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.SHOULDERS,
            name = "Разведения",
            repetitions = mutableListOf(
                Repetition(id = 51, exerciseId = 21, weight = 14.0, quantity = 15, isDone = false),
                Repetition(id = 52, exerciseId = 21, weight = 16.0, quantity = 12, isDone = false),
                Repetition(id = 53, exerciseId = 21, weight = 16.0, quantity = 10, isDone = false)
            ),
            note = "прожимай, выкладывайся на максимум"
        ),
        Exercise(
            id = 22,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.SHOULDERS,
            name = "Жим Сидя",
            repetitions = mutableListOf(
                Repetition(id = 54, exerciseId = 22, weight = 22.5, quantity = 15, isDone = false),
                Repetition(id = 55, exerciseId = 22, weight = 25.0, quantity = 13, isDone = false),
                Repetition(id = 56, exerciseId = 22, weight = 27.5, quantity = 10, isDone = false)
            ),
            note = "с 25(15) выкладывайся"
        ),
        Exercise(
            id = 23,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.SHOULDERS,
            name = "Задняя",
            repetitions = mutableListOf(
                Repetition(id = 57, exerciseId = 23, weight = 90.0, quantity = 13, isDone = false),
                Repetition(id = 58, exerciseId = 23, weight = 90.0, quantity = 11, isDone = false),
                Repetition(id = 59, exerciseId = 23, weight = 100.0, quantity = 8, isDone = false)
            ),
            note = "последний"
        ),
        Exercise(
            id = 24,
            workoutDayId = 3,
            muscleGroup = MuscleGroup.SHOULDERS,
            name = "Шраги",
            repetitions = mutableListOf(
                Repetition(id = 60, exerciseId = 24, weight = 33.0, quantity = 20, isDone = false),
                Repetition(id = 61, exerciseId = 24, weight = 33.0, quantity = 15, isDone = false),
                Repetition(id = 62, exerciseId = 24, weight = 36.0, quantity = 12, isDone = false)
            ),
            note = null
        )
    )

    val mockAdvice = mutableListOf<Advice>(
        Advice(id = 1, exerciseId = 1, title = "Ручками к себе"),
        Advice(id = 2, exerciseId = 2, title = "Не раскачивай корпус локтями вперед")
    )
}