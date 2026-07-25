package com.example.targym.presentation.mapper

import androidx.annotation.StringRes
import com.example.targym.R
import com.example.targym.domain.usecase.workoutday.WorkoutDayNameError

@get:StringRes
val WorkoutDayNameError.errorMessageRes: Int
    get() = when (this) {
        is WorkoutDayNameError.EmptyName -> R.string.error_empty_name
        is WorkoutDayNameError.DuplicateName -> R.string.error_duplicate_day_name
    }
