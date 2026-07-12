package com.example.targym

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.targym.ui.theme.TarGYMTheme

class MainActivity : ComponentActivity() {
    val exercise = Exercise("Приседание со штангой", repetitions = listOf(Repetition(100.0, 12)))
    val advices = listOf(Advice("спину прямо"), Advice("коленки наружу"), Advice("коленки наружу"))

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
