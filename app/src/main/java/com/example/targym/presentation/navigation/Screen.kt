package com.example.targym.presentation.navigation

import com.example.targym.domain.model.MuscleGroup
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data object ManageDays : Screen

    @Serializable
    data class Edit(
        val exerciseId: Long,
        val dayId: Long,
        val muscleGroup: MuscleGroup
    ) : Screen

}