package com.example.targym.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.presentation.main.ExerciseUiModel
import com.example.targym.ui.theme.Second

@Composable
fun MuscleGroupSection(
    muscleGroup: MuscleGroup,
    exercises: List<ExerciseUiModel>,
    onRepetitionClick: (Long, Long) -> Unit,
    onMuscleMenu: () -> Unit,
    onVideoClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Second)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MuscleGroupSectionHeader(
            title = stringResource(muscleGroup.titleRes),
            onMuscleMenu = onMuscleMenu
        )

        exercises.forEach { exercise ->
            key(exercise.id) {
                ExerciseCard(
                    exercise = exercise,
                    onRepetitionClick = onRepetitionClick,
                    onVideoClick = onVideoClick,
                    onEditClick = onEditClick
                )
            }
        }
    }
}