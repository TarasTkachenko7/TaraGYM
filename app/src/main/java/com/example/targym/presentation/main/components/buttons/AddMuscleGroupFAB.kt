package com.example.targym.presentation.main.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.InterFont

@Composable
fun AddMuscleGroupFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        containerColor = Accent,
        contentColor = Black,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .defaultMinSize(minHeight = 50.dp, minWidth = 1.dp)
            .navigationBarsPadding()
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = stringResource(R.string.muscle_group),
            style = TextStyle(
                fontFamily = InterFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.5.sp
            )
        )
    }
}