package com.example.targym.presentation.mapper

import androidx.annotation.StringRes
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.R

@get:StringRes
val MuscleGroup.titleRes: Int
    get() = when (this) {
        MuscleGroup.CHEST -> R.string.chest
        MuscleGroup.BACK -> R.string.back
        MuscleGroup.LEGS -> R.string.legs
        MuscleGroup.SHOULDERS -> R.string.shoulders
        MuscleGroup.TRICEPS -> R.string.triceps
        MuscleGroup.BICEPS -> R.string.biceps
        MuscleGroup.ABS -> R.string.abs
    }