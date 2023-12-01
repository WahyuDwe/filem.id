package com.dwi.filmid.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMsg: String): ApiResponse<Nothing>()
    data object Empty : ApiResponse<Nothing>()
}