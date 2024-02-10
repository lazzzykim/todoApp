package com.example.demo.domain.comment.repository

import com.example.demo.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?
}