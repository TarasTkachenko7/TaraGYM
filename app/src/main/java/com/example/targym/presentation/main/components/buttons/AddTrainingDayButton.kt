package com.example.targym.presentation.main.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.R
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.ButtonsTextStyle
import com.example.targym.ui.theme.InterFont

@Composable
fun AddTrainingDayButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            contentColor = Black,
        ),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = modifier
            .widthIn(max = 300.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.add_a_training_day),
            style = ButtonsTextStyle.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}