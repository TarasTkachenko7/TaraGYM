package com.example.targym.presentation.mapper

import androidx.annotation.StringRes
import com.example.targym.R
import com.example.targym.domain.usecase.workoutday.AddWorkoutDayError
import com.example.targym.domain.usecase.workoutday.UpdateWorkoutNameError

@get:StringRes
val AddWorkoutDayError.errorMessageRes: Int
    get() = when (this) {
        is AddWorkoutDayError.EmptyName -> R.string.error_empty_name
        is AddWorkoutDayError.DuplicateName -> R.string.error_duplicate_day_name
    }

@get:StringRes
val UpdateWorkoutNameError.errorMessageRes: Int
    get() = when(this) {
        is UpdateWorkoutNameError.EmptyName -> R.string.error_empty_name
    }