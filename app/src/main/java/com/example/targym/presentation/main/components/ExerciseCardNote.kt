package com.example.targym.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont

@Composable
fun ExerciseCardNote(
    note: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(Accent.copy(alpha = 0.1f))
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                drawLine(
                    color = Accent,
                    start = Offset(x = strokeWidth / 2, y = 0f),
                    end = Offset(x = strokeWidth / 2, y = size.height),
                    strokeWidth = strokeWidth
                )
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.FlashOn,
            contentDescription = null,
            tint = Accent,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = note,
            style = TextStyle(
                fontSize = 14.sp,
                color = FirstText.copy(alpha = 0.85F),
                fontFamily = InterFont,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )
        )
    }
}