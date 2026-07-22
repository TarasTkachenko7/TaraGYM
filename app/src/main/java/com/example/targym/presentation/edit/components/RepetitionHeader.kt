package com.example.targym.presentation.edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun RepetitionsHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.reps),
            style = TextStyle(
                fontSize = 14.sp,
                color = SecondText,
                fontWeight = FontWeight.Medium,
                fontFamily = InterFont,
                letterSpacing = 0.5.sp,
            ),
        )

        Text(
            text = stringResource(R.string.kg_reps),
            style = TextStyle(
                fontSize = 11.sp,
                color = SecondText,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp,
                fontFamily = InterFont
            ),
        )
    }
}