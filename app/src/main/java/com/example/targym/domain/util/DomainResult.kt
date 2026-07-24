package com.example.targym.domain.util

sealed interface DomainResult<out D, out E> {
    data class Success<out D>(val data: D) : DomainResult<D, Nothing>
    data class Error<out E>(val error: E) : DomainResult<Nothing, E>
}

