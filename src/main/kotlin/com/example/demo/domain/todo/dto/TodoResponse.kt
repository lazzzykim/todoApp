package com.example.demo.domain.todo.dto

data class TodoResponse(
    val id: Long,
//    val author: String, // 회원가입 후 수정
    val title: String,
    val description: String,
)
