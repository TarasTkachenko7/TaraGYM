package com.example.targym.presentation.edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont

@Composable
fun ExerciseTitleSection(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.exercise),
            style = TextStyle(
                fontSize = 14.sp,
                color = Accent,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
                fontFamily = InterFont
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = TextStyle(
                fontSize = 32.sp,
                color = FirstText,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
                fontFamily = InterFont
            )
        )
    }
}