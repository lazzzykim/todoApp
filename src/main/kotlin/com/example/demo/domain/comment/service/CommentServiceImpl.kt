package com.example.demo.domain.comment.service

import com.example.demo.domain.comment.dto.AddCommentRequest
import com.example.demo.domain.comment.dto.CommentResponse
import com.example.demo.domain.comment.dto.UpdateCommentRequest
import com.example.demo.domain.comment.model.Comment
import com.example.demo.domain.comment.model.toResponse
import com.example.demo.domain.comment.repository.CommentRepository
import com.example.demo.domain.exception.InvalidPasswordException
import com.example.demo.domain.exception.ModelNotFoundException
import com.example.demo.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun addComment(
        todoId: Long, request: AddCommentRequest,
    ): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo", todoId)

        val comment = Comment(
            password = request.password,
            content = request.content,
            todo = todo
        )
        return commentRepository.save(comment).toResponse()
    }

    override fun updateComment(
        todoId: Long,
        commentId: Long,
        request: UpdateCommentRequest,
    ): CommentResponse {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        if (comment.password != request.password)
            throw InvalidPasswordException()

        comment.content = request.content
        comment.modifiedDate = LocalDateTime.now()

        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(todoId: Long, commentId: Long) {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        commentRepository.delete(comment)
    }
}