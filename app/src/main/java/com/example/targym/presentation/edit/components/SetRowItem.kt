package com.example.targym.presentation.edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText
import com.example.targym.R
import com.example.targym.ui.theme.Garbage

@Composable
fun SetRowItem(
    setNumber: Int,
    weight: String,
    reps: String,
    onWeightChange: (String) -> Unit,
    onRepsChange: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Second, RoundedCornerShape(12.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${setNumber}.",
            style = TextStyle(
                color = SecondText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFont,
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(70.dp)
            ) {
                BasicTextField(
                    value = weight,
                    onValueChange = onWeightChange,
                    textStyle = TextStyle(
                        color = FirstText,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFont,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    cursorBrush = SolidColor(Accent),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.Center) {
                            if (weight.isEmpty()) {
                                Text(
                                    "--",
                                    style = TextStyle(color = FirstText, fontSize = 22.sp, textAlign = TextAlign.Center),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(top = 2.dp), color = SecondText, thickness = 1.dp)
            }
            Text(
                text = stringResource(R.string.kg),
                style = TextStyle(color = SecondText, fontSize = 14.sp, fontFamily = InterFont),
                modifier = Modifier.padding(start = 6.dp, bottom = 4.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(60.dp)
            ) {
                BasicTextField(
                    value = reps,
                    onValueChange = onRepsChange,
                    textStyle = TextStyle(
                        color = FirstText,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = InterFont,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    cursorBrush = SolidColor(Accent),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.Center) {
                            if (reps.isEmpty()) {
                                Text(
                                    "--",
                                    style = TextStyle(color = FirstText, fontSize = 22.sp, textAlign = TextAlign.Center),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(top = 2.dp), color = SecondText, thickness = 1.dp)
            }
            Text(
                text = stringResource(R.string.x),
                style = TextStyle(color = SecondText, fontSize = 14.sp, fontFamily = InterFont),
                modifier = Modifier.padding(start = 6.dp, bottom = 4.dp)
            )
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(R.string.delete_rep),
                tint = Garbage.copy(alpha = 0.5F),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}