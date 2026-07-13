package com.example.targym.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.targym.domain.model.Advice
import com.example.targym.domain.model.Exercise
import com.example.targym.domain.model.Repetition
import com.example.targym.presentation.edit.EditScreen
import com.example.targym.ui.theme.TarGYMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarGYMTheme() {
                Scaffold { innerPadding ->
                    EditScreen(onNavigationClick = {}, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}