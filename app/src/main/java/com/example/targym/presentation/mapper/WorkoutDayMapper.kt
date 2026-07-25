package com.example.targym.presentation.mapper

import com.example.targym.domain.model.WorkoutDay
import com.example.targym.presentation.model.WorkoutDayUiModel

fun WorkoutDay.toUiModel(): WorkoutDayUiModel {
    return WorkoutDayUiModel(
        id = id,
        name = name,
        isSelected = false
    )
}