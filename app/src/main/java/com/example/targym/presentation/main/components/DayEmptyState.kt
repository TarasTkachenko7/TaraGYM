package com.example.targym.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.SecondText

@Composable
fun DayEmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.FitnessCenter,
            contentDescription = null,
            tint = SecondText,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.empty_day),
            style = TextStyle(
                fontSize = 24.sp,
                color = FirstText,
                fontFamily = InterFont,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.widthIn(max = 320.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.add_first_muscle),
            style = TextStyle(
                fontSize = 14.sp,
                color = SecondText,
                fontFamily = InterFont,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            ),
            modifier = Modifier.widthIn(max = 280.dp),
            textAlign = TextAlign.Center
        )
    }
}