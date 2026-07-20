package com.example.targym.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data object ManageDays : Screen

}