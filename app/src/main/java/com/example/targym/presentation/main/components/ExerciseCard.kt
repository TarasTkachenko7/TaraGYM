package com.example.targym.presentation.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.targym.presentation.main.ExerciseUiModel
import com.example.targym.presentation.main.components.buttons.EditButton
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Border

@Composable
fun ExerciseCard(
    exercise: ExerciseUiModel,
    onRepetitionClick: (Long, Long) -> Unit,
    onVideoClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Background)
            .border(
                BorderStroke(1.dp, Border),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        ExerciseCardHeader(
            name = exercise.name,
            onVideoClick = { onVideoClick(exercise.id) }
        )

        if (!exercise.note.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            ExerciseCardNote(note = exercise.note)
        }

        Spacer(modifier = Modifier.height(12.dp))

        exercise.repetitions.forEach { repetition ->
            key(repetition.id) {
                SingleRepetition(
                    repetition = repetition,
                    onCheckClick = { onRepetitionClick(exercise.id, repetition.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        EditButton(
            onClick = { onEditClick(exercise.id) },
            modifier = Modifier.align(Alignment.End)
        )
    }
}