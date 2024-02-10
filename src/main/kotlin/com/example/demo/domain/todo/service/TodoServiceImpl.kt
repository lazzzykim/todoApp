package com.example.demo.domain.todo.service

import com.example.demo.domain.exception.ModelNotFoundException
import com.example.demo.domain.todo.dto.CreateTodoRequest
import com.example.demo.domain.todo.dto.TodoResponse
import com.example.demo.domain.todo.dto.UpdateTodoRequest
import com.example.demo.domain.todo.model.Todo
import com.example.demo.domain.todo.model.toResponse
import com.example.demo.domain.todo.repository.TodoRepository
import com.example.demo.infra.aop.StopWatch
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
): TodoService {
    override fun getTodoList(): List<TodoResponse> {
        return todoRepository.findAll()
            .map { it.toResponse() }
    }


    @StopWatch
    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo", todoId)

        return TodoResponse(
            id = todoId!!,
            title = todo.title,
            description = todo.description
        )
    }
    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        val todo = Todo(
            title = request.title,
            description = request.description,
        )

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun updateTodo(
        todoId: Long,
        request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo",todoId)

        todo.title = request.title
        todo.description = request.description

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo",todoId)

        return todoRepository.delete(todo)
    }
}