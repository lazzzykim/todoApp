package com.example.demo.domain.todo.dto

data class CreateTodoRequest(
    val title: String,
    val description: String,
    val author: String
)
