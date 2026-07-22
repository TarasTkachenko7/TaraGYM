package com.example.targym.presentation.gif

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.MuscleGroup
import com.example.targym.domain.model.Repetition
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@Composable
fun GifScreen(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(Second)
                .border(
                    BorderStroke(1.dp, Border),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = exercise.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = FirstText,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Border
            )

            Box(
                modifier = Modifier
                    .height(400.dp)
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(SecondText)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent,
                    contentColor = Black
                ),
                elevation = null
            ) {
                Text(
                    text = "ПОНЯТНО",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = InterFont,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GifPreview() {
    GifScreen(
        Exercise(
            id = 1, workoutDayId = 1, muscleGroup = MuscleGroup.CHEST, name = "Приседание со штангой", note = "Глубже",
            repetitions = listOf(
                Repetition(1, 1, 100.0, 8, isDone = true),
                Repetition(1, 1, 100.0, 8, isDone = true),
                Repetition(1, 1, 105.0, 6, isDone = false)
            )
        ),
    )
}