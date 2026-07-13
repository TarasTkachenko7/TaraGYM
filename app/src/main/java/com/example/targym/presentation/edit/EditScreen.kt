package com.example.targym.presentation.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targym.ui.theme.Accent
import com.example.targym.ui.theme.Background
import com.example.targym.ui.theme.Black
import com.example.targym.ui.theme.Border
import com.example.targym.ui.theme.FirstText
import com.example.targym.ui.theme.InterFont
import com.example.targym.ui.theme.Second
import com.example.targym.ui.theme.SecondText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var w1 by rememberSaveable { mutableStateOf("100") }
    var r1 by rememberSaveable { mutableStateOf("10") }
    var w2 by rememberSaveable { mutableStateOf("105") }
    var r2 by rememberSaveable { mutableStateOf("8") }
    var w3 by rememberSaveable { mutableStateOf("") }
    var r3 by rememberSaveable { mutableStateOf("") }
    var noteText by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = Background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Редактирование",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = FirstText,
                            fontFamily = InterFont,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = FirstText,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = FirstText,
                    navigationIconContentColor = FirstText
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "УПРАЖНЕНИЕ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Accent,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        fontFamily = InterFont
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Приседание со штангой",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = FirstText,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        fontFamily = InterFont
                    ),
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Подходы",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = SecondText,
                            fontWeight = FontWeight.Medium,
                            fontFamily = InterFont,
                            letterSpacing = 0.5.sp,
                        ),
                    )

                    Text(
                        text = "КГ / ПОВТОРЫ",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = SecondText,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            fontFamily = InterFont
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                SetRowItem(1, w1, r1, { w1 = it }, { r1 = it }, {})
                Spacer(modifier = Modifier.height(10.dp))

                SetRowItem(2, w2, r2, { w2 = it }, { r2 = it }, {})
                Spacer(modifier = Modifier.height(10.dp))

                SetRowItem(3, w3, r3, { w3 = it }, { r3 = it }, {})

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Border, RoundedCornerShape(12.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = null,
                        tint = SecondText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Добавить подход",
                        style = TextStyle(
                            color = SecondText,
                            fontSize = 14.sp,
                            fontFamily = InterFont,
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Заметки",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Accent,
                        fontWeight = FontWeight.Medium,
                        fontFamily = InterFont
                    ),
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    placeholder = {
                        Text("Добавь детали выполнения...", color = SecondText, fontSize = 14.sp)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(color = FirstText, fontSize = 14.sp, fontFamily = InterFont),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Second,
                        unfocusedContainerColor = Second,
                        focusedBorderColor = SecondText,
                        unfocusedBorderColor = Second
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent,
                        contentColor = Black
                    ),
                    elevation = null
                ) {
                    Text(
                        text = "СОХРАНИТЬ",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = InterFont,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 1.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

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
            .padding(horizontal = 12.dp, vertical = 10.dp),
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
            modifier = Modifier
                .padding(horizontal = 20.dp)
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
                    decorationBox = { innerTextField ->
                        if (weight.isEmpty()) {
                            Text(
                                "--",
                                style = TextStyle(color = FirstText, fontSize = 22.sp, textAlign = TextAlign.Center),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(top = 2.dp), color = SecondText, thickness = 1.dp)
            }
            Text(
                text = "кг",
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
                    decorationBox = { innerTextField ->
                        if (reps.isEmpty()) {
                            Text(
                                "--",
                                style = TextStyle(color = FirstText, fontSize = 22.sp, textAlign = TextAlign.Center),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(top = 2.dp), color = SecondText, thickness = 1.dp)
            }
            Text(
                text = "х",
                style = TextStyle(color = SecondText, fontSize = 14.sp, fontFamily = InterFont),
                modifier = Modifier.padding(start = 6.dp, bottom = 4.dp)
            )
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Удалить подход",
                tint = SecondText,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewEditScreen() {
    EditScreen(onNavigationClick = {})
}