package com.example.demo.domain.todo.service

import com.example.demo.domain.todo.dto.CreateTodoRequest
import com.example.demo.domain.todo.dto.TodoResponse
import com.example.demo.domain.todo.dto.UpdateTodoRequest

interface TodoService {

    fun getTodoList() : List<TodoResponse>

    fun getTodoById(todoId: Long) : TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long)
}