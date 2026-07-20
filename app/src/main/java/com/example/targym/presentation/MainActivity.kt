package com.example.targym.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.targym.presentation.days.ManageDaysScreen
import com.example.targym.presentation.days.ManageDaysViewModel
import com.example.targym.presentation.main.MainScreen
import com.example.targym.presentation.main.MainViewModel
import com.example.targym.presentation.navigation.AppNavHost
import com.example.targym.ui.theme.TarGYMTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            TarGYMTheme() {
                Scaffold { innerPadding ->
                    AppNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
