package com.example.targym.presentation.util

fun Double.formatWeight(): String {
    return if (this % 1 == 0.0) {
        this.toInt().toString()
    } else {
        this.toString()
    }
}