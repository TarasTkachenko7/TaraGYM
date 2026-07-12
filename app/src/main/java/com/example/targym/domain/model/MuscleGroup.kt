package com.example.targym.domain.model

import androidx.annotation.StringRes
import com.example.targym.R

enum class MuscleGroup(@StringRes val titleRes: Int) {
    CHEST(R.string.breast),
    BACK(R.string.back),
    LEGS(R.string.legs),
    TRICEPS(R.string.triceps),
    BICEPS(R.string.biceps),
    ABS(R.string.abs)
}