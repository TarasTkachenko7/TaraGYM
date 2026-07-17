package com.example.targym.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.targym.presentation.main.components.buttons.VideoButton
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont

@Composable
fun ExerciseCardHeader(
    name: String,
    onVideoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 20.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.weight(1f)
        )

        VideoButton(onClick = onVideoClick)
    }
}